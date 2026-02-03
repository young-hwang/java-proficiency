package me.chat.server;

import java.io.IOException;

public interface CommandManager {
    void execute(String received, Session session) throws IOException;
}
