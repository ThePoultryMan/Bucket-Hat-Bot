package io.github.thepoultryman.buckethat.command.global;

import io.github.thepoultryman.buckethat.command.global.commands.GitHubCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GlobalCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        if (commandName.equals("github")) {
            if (event.getSubcommandName().equals("repo")) {
                String repository = null;
                Integer issueNumber = null;
                if (event.getOption("repository") != null)
                    repository = event.getOption("repository").getAsString();
                if (event.getOption("issue") != null)
                    issueNumber = event.getOption("issue").getAsInt();

                if (GlobalCommandHelper.getCommand(commandName) instanceof GitHubCommand gitHubCommand)
                    event.replyEmbeds(gitHubCommand.getEmbedResponse(repository, issueNumber, event.getUser().getName())).queue();
            } else {
                String username = null;
                if (event.getOption("name") != null)
                    username = event.getOption("name").getAsString();

                if (GlobalCommandHelper.getCommand(commandName) instanceof GitHubCommand gitHubCommand)
                    event.replyEmbeds(gitHubCommand.getUserEmbed(username)).queue();
            }
        }
    }
}
