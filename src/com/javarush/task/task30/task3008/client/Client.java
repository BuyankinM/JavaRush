package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected;

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Присоединился новый участник: " + userName);
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник " + userName + "покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String userName = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, userName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                String text = message.getData();
                MessageType type = message.getType();

                if (type == MessageType.TEXT) processIncomingMessage(text);
                else if (type == MessageType.USER_ADDED) informAboutAddingNewUser(text);
                else if (type == MessageType.USER_REMOVED) informAboutDeletingNewUser(text);
                else throw new IOException("Unexpected MessageType");
            }
        }

        @Override
        public void run() {
            String host = getServerAddress();
            int port = getServerPort();
            try (Socket socket = new Socket(host, port);) {
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (Exception e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    protected String getServerAddress() {
        System.out.println("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        System.out.println("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        System.out.println("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения!");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Ошибка");
                return;
            }
        }

        if (clientConnected) {
            System.out.println("Соединение установлено.\nДля выхода наберите команду 'exit'.");
        } else {
            System.out.println("Произошла ошибка во время работы клиента.");
        }

        while (clientConnected) {
            String command = ConsoleHelper.readString();
            if (command.equals("exit")) break;
            if (shouldSendTextFromConsole()) {
                sendTextMessage(command);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
