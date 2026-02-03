package me.io.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static me.util.MyLogger.log;

public class ClientV5 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("Start Client");

        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            log("Connect Socket: " + socket);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // 서버에 문자 보내기
                System.out.print("Send Message: ");
                String toSend = scanner.nextLine();
                dataOutputStream.writeUTF(toSend);
                log("client -> server: " + toSend);

                // 서버로부터 문자 받기
                String received = dataInputStream.readUTF();
                log("client <- server: " + received);

                if (toSend.equals("bye")) {
                    break;
                }
            }

            log("close connection" + socket);
        } catch (IOException e) {
            log(e);
        }
    }
}
