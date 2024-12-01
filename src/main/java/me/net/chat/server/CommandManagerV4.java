package me.net.chat.server;

import me.net.chat.server.command.ChangeCommand;
import me.net.chat.server.command.Command;
import me.net.chat.server.command.DefaultCommand;
import me.net.chat.server.command.ExitCommand;
import me.net.chat.server.command.JoinCommand;
import me.net.chat.server.command.MessageCommand;
import me.net.chat.server.command.UsersCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV4 implements CommandManager {
    private final String DELIMETER = " | ";
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV4(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand(sessionManager));
    }

    @Override
    public void execute(String payload, Session session) throws IOException {
        String[] split = payload.split(DELIMETER);
        Command command = commands.getOrDefault(split[0], defaultCommand);
        command.execute(split, session);
    }
}
