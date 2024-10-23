package me.io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class NewFilesMain {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exists: " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(directory);
            System.out.println("Directory created");
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean isFile = Files.isRegularFile(file);
        System.out.println("isFile = " + isFile);

        boolean isDirectory = Files.isDirectory(directory);
        System.out.println("isDirectory = " + isDirectory);

        long size = Files.size(file);
        System.out.println("size = " + size + " bytes");

        Path newPath = Path.of("temp/newExample.txt");
        Files.move(file, newPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved");

        FileTime lastModifiedTime = Files.getLastModifiedTime(newPath);
        System.out.println("lastModifiedTime = " + lastModifiedTime);

        BasicFileAttributes basicFileAttributes = Files.readAttributes(newPath, BasicFileAttributes.class);
        System.out.println("========= Attributes =========");
        System.out.println("Creation time: " + basicFileAttributes.creationTime());
        System.out.println("Is directory: " + basicFileAttributes.isDirectory());
        System.out.println("Is file: " + basicFileAttributes.isRegularFile());
        System.out.println("Is Symlink: " + basicFileAttributes.isSymbolicLink());
        System.out.println("Last modified: " + basicFileAttributes.lastModifiedTime());

        try {
            Files.delete(file);
            System.out.println("File deleted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
