package io.github.thepoultryman.buckethat.command.global;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class GlobalCommand {
    private final String name;
    private final String description;

    public GlobalCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SlashCommandData getCommandData() {
        return Commands.slash(name, description);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isResponseEmbed() {
        return false;
    }

    public String getStringResponse() {
        return null;
    }

    public MessageEmbed getEmbedResponse() {
        return null;
    }
}
