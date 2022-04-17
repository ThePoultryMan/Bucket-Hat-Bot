package io.github.thepoultryman.buckethat.command.global;

import java.util.HashMap;
import java.util.Map;

public class GlobalCommandHelper {
    private static final Map<String, GlobalCommand> globalCommandMap = new HashMap<>();

    public static GlobalCommand getCommand(String commandName) {
        return globalCommandMap.get(commandName);
    }

    public static void addCommand(GlobalCommand globalCommand) {
        globalCommandMap.put(globalCommand.getName(), globalCommand);
    }
}
