package io.github.thepoultryman.buckethat.command.global;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class GlobalCommand {
    private final String name;
    private final String description;
    private final SlashCommandData commandData;

    public GlobalCommand(String name, String description) {
        this.name = name;
        this.description = description;
        this.commandData = Commands.slash(name, description);
    }

    public SlashCommandData getCommandData() {
        return this.commandData;
    }

    public void addOptions(OptionData... options) {
        this.commandData.addOptions(options);
    }

    public void addSubcommands(SubcommandData... subcommands) {
        this.commandData.addSubcommands(subcommands);
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
