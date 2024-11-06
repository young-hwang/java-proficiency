package me.net.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class DatagramClientSample {
    public static void main(String[] args) {
        DatagramClientSample sample = new DatagramClientSample();
        sample.sendDatagramSample();
    }

    private void sendDatagramSample() {
        for (int loop = 0; loop < 3; loop++) {
            sendDatagramData("I liked udp " + new Date());
        }
        sendDatagramData("EXIT");
    }

    private void sendDatagramData(String data) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] bytes = data.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, address, 9999);
            client.send(datagramPacket);
            System.out.println("Client: Send data");
            client.close();
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
