package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name = "";
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() != MessageType.USER_NAME) continue;

                name = answer.getData();
                if (name.isEmpty() || connectionMap.containsKey(name)) continue;

                connectionMap.put(name, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                break;
            }
            return name;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                String name = entry.getKey();
                if (!name.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() != MessageType.TEXT) {
                    ConsoleHelper.writeMessage("Ошибка обработки сообщения");
                    continue;
                }

                String text = String.format("%s: %s", userName, message.getData());
                sendBroadcastMessage(new Message(MessageType.TEXT, text));
            }
        }

        @Override
        public void run() {
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            ConsoleHelper.writeMessage("Установлено новое соединение с адресом: " + socketAddress.toString());

            try (Connection connection = new Connection(socket)) {
                String name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                notifyUsers(connection, name);
                serverMainLoop(connection, name);

                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Ошибка обмена");
            }

            ConsoleHelper.writeMessage("Соединение закрыто!");
        }

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            Connection connection = entry.getValue();
            try {
                connection.send(message);
            } catch (IOException e) {
                System.out.println("Ошибка отправки сообщения");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Чат сервер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Произошла ошибка при запуске или работе сервера.");
        }
    }
}
