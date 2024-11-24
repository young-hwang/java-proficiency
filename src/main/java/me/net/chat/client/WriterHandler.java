package me.net.chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static me.util.MyLogger.log;

public class WriterHandler implements Runnable{
    private final String DELIMETER = " | ";
    private DataOutputStream output;
    private Client client;
    private boolean isClosed = false;

    public WriterHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMETER + username);
            while(true) {
                String toSend = scanner.nextLine();
                if (toSend.isEmpty()) {
                    continue;
                }

                if (toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                if (toSend.startsWith("/")) {
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("/message" + DELIMETER + toSend);
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    private String inputUsername(Scanner scanner) {
        System.out.print("Input username: ");
        String username;
        do {
            username = scanner.nextLine();
        } while(username.isEmpty());
        return username;
    }

    public synchronized void close() {
        if (isClosed) {
            return;
        }
        try {
            System.in.close();
        } catch (IOException e) {
            log(e);
        }
        isClosed = true;
        log("Closed WriteHandler");
    }
}
