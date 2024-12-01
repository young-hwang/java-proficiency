package me.net.chat.server.command;

import me.net.chat.server.Session;
import me.net.chat.server.SessionManager;

import java.io.IOException;
import java.util.List;

public class ExitCommand implements Command {
    private final SessionManager sessionManager;

    public ExitCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        List<String> allUserName = sessionManager.getAllUserName();
        session.send("All Users : " + String.join(", ", allUserName));
    }
}
