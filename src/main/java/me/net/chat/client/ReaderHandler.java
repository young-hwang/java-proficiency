package me.net.chat.client;

import java.io.DataInputStream;
import java.io.IOException;

import static me.util.MyLogger.log;

public class ReaderHandler implements Runnable {
    private final DataInputStream input;
    private final Client client;
    private Boolean isClosed = false;

    public ReaderHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String received = input.readUTF();
                System.out.println(received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() {
        if (isClosed) return;
        isClosed = true;
        log("Close ReaderHandler");
    }
}
