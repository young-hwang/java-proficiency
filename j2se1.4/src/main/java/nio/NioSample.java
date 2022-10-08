package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.io.File.separator;

public class NioSample {
    public static void main(String[] args) {
        NioSample nioSample = new NioSample();
        nioSample.basicWriteAndRead();
    }

    private void basicWriteAndRead() {
        String fileName = new File(".").getAbsolutePath() + separator + "nio.txt";
        try {
            writeFile(fileName, "My first NIO sample");
            readFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile(String fileName) throws IOException {
        FileChannel channel = new FileInputStream(fileName).getChannel();   // file channel 생성
        ByteBuffer buffer = ByteBuffer.allocate(1024);  // allocate() 메소드를 통하여 buffer 생성, 매개 변수는 저장되는 크기
        channel.read(buffer);   // read()에 buffer를 넘겨 buffer에 데이터를 담는다.
        buffer.flip();  // flip() 메소드는 buffer에 담겨있는 데이터의 가장 앞으로 이동
        while(buffer.hasRemaining()) {
            System.out.print((char)buffer.get());   // 한 byte 씩 읽는 작업 수행
        }
        channel.close();
    }

    private void writeFile(String fileName, String data) throws IOException {
        FileChannel channel = new FileOutputStream(fileName).getChannel(); // file channel 생성
        byte[] bytes = data.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes); // ByteBuffer.wrap() 호출 시 ByteBuffer 객체 생성
        channel.write(buffer);  // write() 메소드에 buffer 객체를 넘겨주면 파일에 쓰게 됨
        System.out.println("Success write");
        channel.close();
    }
}
