package me.io.net.exception.close.normal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import static me.util.MyLogger.log;

public class NormalCloseClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("Connect Socket: " + socket);
        InputStream input = socket.getInputStream();
//        readByInputStream(input, socket);
//        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        log("Closed Socket: " + socket);
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(input);
        try {
            String s = dataInputStream.readUTF();
        }  catch (EOFException e) {
            log(e);
        } finally {
            dataInputStream.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String s = bufferedReader.readLine();
        log("Read line: " + s);
        if (s == null) {
            bufferedReader.close();
            socket.close();
        }
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        log("Read: " + read);
        if (read == -1) {
            input.close();
            socket.close();
        }
    }
}
