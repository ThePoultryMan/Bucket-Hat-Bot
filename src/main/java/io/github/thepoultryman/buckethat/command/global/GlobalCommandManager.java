package io.github.thepoultryman.buckethat.command.global;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Collection;

public class GlobalCommandManager {
    private final JDA jda;

    public GlobalCommandManager(JDA jda) {
        this.jda = jda;
    }

    public void addAllCommands(Collection<SlashCommandData> commands) {
        jda.updateCommands().addCommands(commands).queue();
    }

    public void clearAllCommands() {
        jda.updateCommands().queue();
    }
}
