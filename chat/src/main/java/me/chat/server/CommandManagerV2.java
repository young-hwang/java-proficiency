package me.chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {
    private final SessionManager sessionManager;
    private final String DELIMETER = " | ";

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String payload, Session session) throws IOException {
        if (payload.startsWith("/join")) {
            String[] split = payload.split(DELIMETER);
            String username = split[2];
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장했습니다.");
        } else if (payload.startsWith("/message")) {
            String[] split = payload.split(DELIMETER);
            String username = session.getUsername();
            String message = split[2];
            sessionManager.sendAll("[" + username + "] : " + message);
        } else if (payload.startsWith("/change")) {
            String[] split = payload.split(DELIMETER);
            String oldName = session.getUsername();
            String newName = split[2];
            session.setUsername(newName);
            sessionManager.sendAll("Changed Name: " + oldName + " -> " + newName);
        } else if (payload.startsWith("/users")) {
            List<String> allUserName = sessionManager.getAllUserName();
            session.send("All Users : " + String.join(", ", allUserName));
        } else if (payload.startsWith("/exit")) {
            throw new IOException("exit");
        } else {
            session.send("Invalid command: " + payload);
        }
    }
}
