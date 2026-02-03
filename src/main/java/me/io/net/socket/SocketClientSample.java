package me.io.net.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class SocketClientSample {
    public static void main(String[] args) {
        SocketClientSample sample = new SocketClientSample();
        sample.sendAndReceiveSocketData();
    }

    private void sendSocketSample() {
        for (int loop = 0; loop < 3; loop++) {
            sendSocketData("I liked java at " + new Date());
        }
    }

    private void sendSocketData(String data) {
        Socket socket = null;
        try {
            System.out.println("Client: Connecting..");
            socket = new Socket("127.0.0.1", 9999);
            System.out.println("Client: Connect status=" + socket.isConnected());
            Thread.sleep(1000);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] bytes = data.getBytes();
            bufferedOutputStream.write(bytes);
            System.out.println("Client: Sent data");
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void sendAndReceiveSocketData() {
        Socket socket = null;
        try {
            System.out.println("Client: Connecting..");
            socket = new Socket("127.0.0.1", 9999);
            System.out.println("Client: Connect status=" + socket.isConnected());
            Thread.sleep(1000);
            InputStream outputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(outputStream));
            String data = null;
            StringBuilder receivedData = new StringBuilder();
            while ((data = reader.readLine()) != null) {
                receivedData.append(data);
            }
            System.out.println(receivedData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
