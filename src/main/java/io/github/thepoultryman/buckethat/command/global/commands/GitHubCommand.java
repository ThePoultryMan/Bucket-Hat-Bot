package io.github.thepoultryman.buckethat.command.global.commands;

import io.github.thepoultryman.buckethat.BucketHat;
import io.github.thepoultryman.buckethat.command.global.GlobalCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.kohsuke.github.GHRepository;

public class GitHubCommand extends GlobalCommand {
    public GitHubCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isResponseEmbed() {
        return true;
    }

    public MessageEmbed getEmbedResponse(String repository, Integer issue) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("GitHub");

        if (repository == null && issue == null) {
            embedBuilder.setTitle("Bucket Hat Bot")
                    .addField("Issues (Open)", BucketHat.gitHub.getOpenIssueCount("ThePoultryMan/Bucket-Hat-Bot").toString(), false);
        } else if (repository != null && issue != null && issue <= 0) {
            embedBuilder.setTitle("silly g00se")
                    .setDescription("1. There cannot be issues with a issue 0 or lower.\n2. This means that Bucket-Hat-Bot cannot have an issue " + issue);
        } else if (repository != null && issue == null) {
            GHRepository ghRepository = BucketHat.gitHub.getRepository(repository);
            if (ghRepository != null) {
                embedBuilder.setTitle(ghRepository.getName());
            } else {
                embedBuilder.setTitle("~~oops, something went wrong~~ Invalid Repository")
                        .setDescription("""
                                 The repository needs to be in the User/Repository format, or the Organization/Repository format.
                                **Look at these examples:**
                                - ThePoultryMan/Crops-Love-Rain
                                - IrisShaders/Iris""");
            }
        }

        return embedBuilder.build();
    }
}