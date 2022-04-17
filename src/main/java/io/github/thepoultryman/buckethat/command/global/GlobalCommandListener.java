package io.github.thepoultryman.buckethat.command.global;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GlobalCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        if (commandName.equals("github")) {
            event.replyEmbeds(GlobalCommandHelper.getCommand(commandName).getEmbedResponse()).queue();
        }
    }
}
