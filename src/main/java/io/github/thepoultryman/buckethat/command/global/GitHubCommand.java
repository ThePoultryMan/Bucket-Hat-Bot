package io.github.thepoultryman.buckethat.command.global;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class GitHubCommand extends GlobalCommand {
    public GitHubCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isResponseEmbed() {
        return true;
    }

    @Override
    public MessageEmbed getEmbedResponse() {
        EmbedBuilder messageEmbedBuilder = new EmbedBuilder().setTitle("GitHub");
        return messageEmbedBuilder.build();
    }
}
