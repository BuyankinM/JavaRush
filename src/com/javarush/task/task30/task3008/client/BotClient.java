package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (!message.contains(": ")) return;

            String[] split = message.split(": ");
            SimpleDateFormat fmt = null;
            switch (split[1]) {
                case "дата":
                    fmt = new SimpleDateFormat("d.MM.YYYY");
                    break;
                case "день":
                    fmt = new SimpleDateFormat("d");
                    break;
                case "месяц":
                    fmt = new SimpleDateFormat("MMMM");
                    break;
                case "год":
                    fmt = new SimpleDateFormat("YYYY");
                    break;
                case "время":
                    fmt = new SimpleDateFormat("H:mm:ss");
                    break;
                case "час":
                    fmt = new SimpleDateFormat("H");
                    break;
                case "минуты":
                    fmt = new SimpleDateFormat("m");
                    break;
                case "секунды":
                    fmt = new SimpleDateFormat("s");
                    break;
            }

            if (fmt != null) {
                Date time = Calendar.getInstance().getTime();
                sendTextMessage(String.format("Информация для %s: %s",
                        split[0],
                        fmt.format(time)));
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (100 * Math.random()));
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
