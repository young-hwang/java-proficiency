package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramServerSample {
    public static void main(String[] args) {
        DatagramServerSample datagramServerSample = new DatagramServerSample();
        datagramServerSample.startServer();
    }

    private void startServer() {
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(9999);
            int bufferLength = 256;
            byte[] bytes = new byte[bufferLength];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bufferLength);
            while(true) {
                System.out.println("Server: Waiting for request.");
                server.receive(datagramPacket);
                int dataLength =datagramPacket.getLength();
                System.out.println("Server: received. Data length=" + dataLength);
                String data = new String(datagramPacket.getData(), 0, dataLength);
                System.out.println("Received data:" + data);
                if(data.equals("EXIT")) {
                    System.out.println("Stop DatagramServer");
                    break;
                }
                System.out.println("...........");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }
}
