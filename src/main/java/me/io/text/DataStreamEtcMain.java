package me.io.text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamEtcMain {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF("회원A");
        dos.writeInt(20);
        dos.writeDouble(10.5);
        dos.writeBoolean(true);
        dos.close();

        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);
        String s = dis.readUTF();
        System.out.println("s = " + s);
        int i = dis.readInt();
        System.out.println("i = " + i);
        double v = dis.readDouble();
        System.out.println("v = " + v);
        boolean b = dis.readBoolean();
        System.out.println("b = " + b);
        dis.close();
    }
}
