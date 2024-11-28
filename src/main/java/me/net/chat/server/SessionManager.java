package me.net.chat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static me.util.MyLogger.log;

public class SessionManager {
    private final List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void sendAll(String s) {
        for (Session session : sessions) {
            try {
                session.send(s);
            } catch (IOException e) {
                log(e);
            }
        }
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public List<String> getAllUserName() {
        return sessions.stream().map(Session::getUsername).collect(Collectors.toList());
    }
}
