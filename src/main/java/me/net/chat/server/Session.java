package me.net.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static me.util.MyLogger.log;

public class Session implements Runnable {
    private final Socket socket;
    private final SessionManager sessionManager;
    private final DataInputStream input;
    private final DataOutputStream output;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while(true) {
            try {
                String s = input.readUTF();
                log(s);
                sessionManager.sendMessage(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void sendMessage(String s) throws IOException {
        output.writeUTF(s);
    }
}
