package me.io.net.socket;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6 {
    private List<ServerV6.Session> sessions = new ArrayList<>();

    public synchronized void add(ServerV6.Session session) {
        sessions.add(session);
    }

    public synchronized void remove(ServerV6.Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for(ServerV6.Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }
}
