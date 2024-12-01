package me.net.chat.server;

import java.io.IOException;

public interface Command {
    void execute(String[] args, Session session) throws IOException;
}
