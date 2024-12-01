package me.chat.server;

import me.chat.server.command.ChangeCommand;
import me.chat.server.command.Command;
import me.chat.server.command.ExitCommand;
import me.chat.server.command.JoinCommand;
import me.chat.server.command.MessageCommand;
import me.chat.server.command.UsersCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV3 implements CommandManager {
    private final String DELIMETER = " | ";
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand(sessionManager));
    }

    @Override
    public void execute(String payload, Session session) throws IOException {
        String[] split = payload.split(DELIMETER);
        Command command = commands.get(split[0]);
        if(command == null) {
            session.send("Invalid command: " + payload);
            return;
        }
        command.execute(split, session);
    }
}
