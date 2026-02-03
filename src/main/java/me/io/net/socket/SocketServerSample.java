package me.io.net.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSample {
    public static void main(String[] args) {
        SocketServerSample server = new SocketServerSample();
        server.startReplyServer();
    }

    private void startServer() {
        ServerSocket serverSocket = null;
        Socket client = null;
        try {
             serverSocket = new ServerSocket(9999);
             while(true) {
                 System.out.println("Server: Waiting for request.");
                 client = serverSocket.accept();
                 System.out.println("Server: Accepted");
                 InputStream stream = client.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                 String data = null;
                 StringBuilder receiveData = new StringBuilder();
                 while((data = reader.readLine()) != null) {
                    receiveData.append(data);
                 }
                 System.out.println("Received data: " + receiveData);
                 reader.close();
                 stream.close();
                 client.close();
                 if(receiveData != null && "EXIT".equals(receiveData.toString())) {
                     System.out.println("Stop SocketServer");
                     break;
                 }
                 System.out.println("------------------");
             }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startReplyServer() {
        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            serverSocket = new ServerSocket(9999);
            while(true) {
                System.out.println("Server: Waiting for request.");
                client = serverSocket.accept();
                System.out.println("Server: Accepted");
                OutputStream stream = client.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
                writer.write("OK");
                writer.close();
                stream.close();
                client.close();
                System.out.println("------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
