package io.github.thepoultryman.buckethat.command.guild;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        if (GuildCommandHelper.isBasicCommand(commandName)) {
            event.reply(GuildCommandHelper.getBasicCommandResponse(commandName)).queue();
        } else if (GuildCommandHelper.isBasicEmbedCommand(commandName)) {
            event.replyEmbeds(GuildCommandHelper.getBasicEmbedCommandResponse(commandName)).queue();
        } else if (GuildCommandHelper.isEmbedCommand(commandName)) {
            event.replyEmbeds(GuildCommandHelper.getEmbedCommandResponse(commandName)).queue();
        }
    }
}
