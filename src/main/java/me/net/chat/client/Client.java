package me.net.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static me.net.socket.SocketCloseUtil.closeAll;
import static me.util.MyLogger.log;

public class Client {
    private final String host;
    private final int port;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private ReaderHandler readerHandler;
    private WriterHandler writeHandler;
    private boolean isClosed = false;


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        this.socket = new Socket(host, port);
        log("Connect Server " + socket);
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.readerHandler = new ReaderHandler(input, this);
        this.writeHandler = new WriterHandler(output, this);
        Thread readerThread = new Thread(readerHandler);
        Thread writerThread = new Thread(writeHandler);
        readerThread.start();
        writerThread.start();
    }

    public synchronized void close() {
        if (isClosed) return;
        closeAll(this.socket, this.input, this.output);
        this.isClosed = true;
    }
}
