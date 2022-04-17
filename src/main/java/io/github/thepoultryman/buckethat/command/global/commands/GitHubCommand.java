package io.github.thepoultryman.buckethat.command.global.commands;

import io.github.thepoultryman.buckethat.BucketHat;
import io.github.thepoultryman.buckethat.command.global.GlobalCommand;
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

    public MessageEmbed getEmbedResponse(String repository, int issue) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("GitHub");

        if (repository == null && issue == 0) {
            embedBuilder.setTitle("Bucket Hat Bot")
                    .addField("Issues (Open)", BucketHat.gitHub.getOpenIssueCount("ThePoultryMan/Bucket-Hat-Bot").toString(), false);
        }

        return embedBuilder.build();
    }
}
