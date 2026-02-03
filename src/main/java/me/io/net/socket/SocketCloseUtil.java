package me.io.net.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static me.util.MyLogger.log;

public class SocketCloseUtil {
    public static void closeAll(Socket socket, InputStream in, OutputStream out) {
        close(out);
        close(in);
        close(socket);
    }

    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            }  catch (Exception e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            }  catch (Exception e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            }  catch (Exception e) {
                log(e.getMessage());
            }
        }
    }
}
