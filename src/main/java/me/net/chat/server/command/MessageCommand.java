package me.net.chat.server.command;

import me.net.chat.server.Session;
import me.net.chat.server.SessionManager;

import java.io.IOException;

public class MessageCommand implements Command {
    private final SessionManager sessionManager;

    public MessageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String username = session.getUsername();
        String message = args[2];
        sessionManager.sendAll("[" + username + "] : " + message);
    }
}
