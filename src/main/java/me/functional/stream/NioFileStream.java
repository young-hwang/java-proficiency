package me.functional.stream;

public class NioFileStream {
    public static void main(String[] args) {
        long uniqueWords = 0;
        try (java.util.stream.Stream<String> lines = java.nio.file.Files.lines(java.nio.file.Paths.get("src/main/resources/data.txt"))) {
            uniqueWords = lines.flatMap(line -> java.util.Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println("uniqueWords : " + uniqueWords);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
