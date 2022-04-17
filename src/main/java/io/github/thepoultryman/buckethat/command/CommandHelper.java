package io.github.thepoultryman.buckethat.command;

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Collection;
import java.util.HashSet;

public class CommandHelper {
    private static final Collection<SlashCommandData> commandCollection = new HashSet<>();
    private static boolean guildCommandsReady;
    private static boolean globalCommandReady;

    public static void addCommandData(SlashCommandData commandData) {
        commandCollection.add(commandData);
    }

    public static Collection<SlashCommandData> getCommandCollection() {
        return commandCollection;
    }

    public static void setGlobalCommandReady(boolean ready) {
        globalCommandReady = ready;
    }

    public static void setGuildCommandsReady(boolean ready) {
        guildCommandsReady = ready;
    }

    public static boolean areCommandsReady() {
        return globalCommandReady && guildCommandsReady;
    }
}
