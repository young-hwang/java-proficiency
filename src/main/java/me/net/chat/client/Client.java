package me.net.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static me.net.socket.SocketCloseUtil.closeAll;

public class Client {
    private final String ip;
    private final int port;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private ReaderHandler readerHandler;
    private WriteHandler writeHandler;
    private boolean isClosed = false;


    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        this.socket = new Socket(ip, port);
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public synchronized void close() {
        if (isClosed) return;
        closeAll(this.socket, this.input, this.output);
        this.isClosed = true;
    }
}
