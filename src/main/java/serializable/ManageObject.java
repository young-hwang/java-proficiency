package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static java.io.File.separator;

public class ManageObject {
    public static void main(String[] args) {
        ManageObject manageObject = new ManageObject();
        String fullPath = separator + "serial.obj";
        SerialDTO dto = new SerialDTO("book", 1, true, 10);
        manageObject.saveObject(fullPath, dto);
        SerialDTO readDto = manageObject.readObject(fullPath);
        System.out.println(readDto.toString());
    }

    private SerialDTO readObject(String fullPath) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            String absolutePath = new File(".").getAbsolutePath();
            fis = new FileInputStream(absolutePath + fullPath);
            ois = new ObjectInputStream(fis);
            SerialDTO dto = (SerialDTO) ois.readObject();
            System.out.println("Read Success");
            return dto;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveObject(String fullPath, SerialDTO dto) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            String absolutePath = new File(".").getAbsolutePath();
            fos = new FileOutputStream(absolutePath + fullPath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dto);
            System.out.println("Write Success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
