package io.github.thepoultryman.buckethat.command.global.commands;

import io.github.thepoultryman.buckethat.BucketHat;
import io.github.thepoultryman.buckethat.GitHubIntegration;
import io.github.thepoultryman.buckethat.command.global.GlobalCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

public class GitHubCommand extends GlobalCommand {
    public GitHubCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isResponseEmbed() {
        return true;
    }

    public MessageEmbed getEmbedResponse(String repository, Integer issue, String requestingName) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("GitHub");

        if (repository == null && issue == null) {
            embedBuilder = this.getRepositoryStatsEmbed("ThePoultryMan/Bucket-Hat-Bot", requestingName);
        } else if (repository != null && issue != null && issue <= 0) {
            embedBuilder.setTitle("silly g00se")
                    .setDescription("1. There cannot be issues with a issue 0 or lower.\n2. This means that Bucket-Hat-Bot cannot have an issue " + issue);
        } else if (repository != null && issue == null) {
            GHRepository ghRepository = BucketHat.gitHub.getRepository(repository);
            if (ghRepository != null) {
                embedBuilder = this.getRepositoryStatsEmbed(repository, requestingName);
            } else {
                embedBuilder.setTitle("~~oops, something went wrong~~ Invalid Repository").setDescription(this.getRepositoryFormatDesc());
            }
        } else if (repository != null) {
            embedBuilder = this.getEmbedIssueResponse(repository, issue);
        } else if (issue >= 1) {
            embedBuilder = this.getEmbedIssueResponse("ThePoultryMan/Bucket-Hat-Bot", issue);
        }

        return embedBuilder.build();
    }

    private EmbedBuilder getRepositoryStatsEmbed(String repository, String requestingName) {
        GitHubIntegration gitHub = BucketHat.gitHub;

        return new EmbedBuilder().setTitle(gitHub.getRepository(repository).getName())
                .setDescription("*Owned by " + gitHub.getRepositoryOwner(repository).getLogin() + "*")
                .addField("Issues (Open)", gitHub.getOpenIssueCount(repository).toString(), false)
                .addField("Stars", gitHub.getStarCount(repository).toString(), false)
                .addField("Watchers", gitHub.getWatcherCount(repository).toString(), false)
                .setFooter("Requested by " + requestingName);
    }

    private String getRepositoryFormatDesc() {
        return """
                  The repository needs to be in the User/Repository format, or the Organization/Repository format.
                  **Look at these examples:**
                  - ThePoultryMan/Crops-Love-Rain
                  - IrisShaders/Iris""";
    }

    private EmbedBuilder getEmbedIssueResponse(String repository, Integer issue) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        GHRepository ghRepository = BucketHat.gitHub.getRepository(repository);
        if (ghRepository != null) {
            try {
                GHIssue ghIssue = ghRepository.getIssue(issue);

                if (ghIssue.getBody().length() <= 1024) {
                    embedBuilder.setTitle(ghRepository.getName() + " Issue #" + issue).addField(ghIssue.getTitle(),
                            ghIssue.getBody(), false);
                } else {
                    String link = "...\n[[Read More]](" + ghIssue.getHtmlUrl() + ")";
                    embedBuilder.setTitle(ghRepository.getName() + " Issue #" + issue).addField(ghIssue.getTitle(),
                            ghIssue.getBody().substring(0, 1024 - link.length()) + link, false);
                }
            } catch (IOException e) {
                return this.getNoIssueFoundEmbed(repository, issue);
            }
        } else {
            embedBuilder.setTitle("~~oops, something went wrong~~ Invalid Repository").setDescription(this.getRepositoryFormatDesc());
        }

        return embedBuilder;
    }

    private EmbedBuilder getNoIssueFoundEmbed(String repository, Integer issue) {
        return new EmbedBuilder().setTitle("#" + issue + " not found in " + repository)
                .setDescription("Issue #" + issue + " could not be found in " + repository + ". This usually means that there is no" +
                        " issue #" + issue + ".");
    }
}
