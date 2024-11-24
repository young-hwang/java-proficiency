package me.net.chat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private final List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void sendMessage(String s) {
        sessions.stream().forEach(session -> {
            try {
                session.sendMessage(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
