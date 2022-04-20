package io.github.thepoultryman.buckethat.command.global;

import io.github.thepoultryman.buckethat.BucketHat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Collection;
import java.util.HashSet;

public class GlobalCommandManager {
    private static final JDA JDA = BucketHat.getJda();
    private static final Collection<SlashCommandData> commands = new HashSet<>();

    public static void addGlobalCommand(SlashCommandData command) {
        commands.add(command);
    }

    public static Collection<SlashCommandData> getGlobalCommands() {
        return commands;
    }

    public static void createGlobalCommands() {
        JDA.updateCommands().addCommands(commands).queue();
    }
}
