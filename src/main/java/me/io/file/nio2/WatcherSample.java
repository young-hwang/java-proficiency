package me.io.file.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class WatcherSample extends Thread {
    private String path;

    public static void main(String[] args) throws Exception {
//        String path = new File(".").getAbsolutePath();
        String path = String.valueOf(Paths.get(".").toAbsolutePath().normalize());
        String fileName = "WatcherSample.txt";
        WatcherSample watcherSample = new WatcherSample(path);
        watcherSample.setDaemon(true);
        watcherSample.start();
        Thread.sleep(1000);
        for (int loop = 0; loop < 10; loop++) {
            watcherSample.fileWriteDelete(path, fileName + loop);
        }
    }

    public WatcherSample(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        System.out.println("Watcher thread is started");
        System.out.format("Dir=%s\n", this.path);
        addWatcher();
    }

    public void addWatcher() {
        try {
            Path dir = Paths.get(this.path);
            WatchService watcher = FileSystems.getDefault().newWatchService();
            WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            while (true) {
                key = watcher.take();
                List<WatchEvent<?>> watchEvents = key.pollEvents();
                for (WatchEvent event : watchEvents) {
                    Path name = (Path) event.context();
                    if (event.kind() == ENTRY_CREATE) {
                        System.out.format("%s create\n", name);
                    } else if (event.kind() == ENTRY_DELETE) {
                        System.out.format("%s delete\n", name);
                    } else if (event.kind() == ENTRY_MODIFY) {
                        System.out.format("%s modify\n", name);
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fileWriteDelete(String dirName, String fileName) {
        Path path = Paths.get(dirName, fileName);
        String contents = "Watcher Sample";
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        try {
            System.out.println("Write " + fileName);
            Files.write(path, contents.getBytes(), openOption);
            Files.delete(path);
            Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
