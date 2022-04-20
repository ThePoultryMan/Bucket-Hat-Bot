package io.github.thepoultryman.buckethat.command.global.commands;

import io.github.thepoultryman.buckethat.BucketHat;
import io.github.thepoultryman.buckethat.GitHubIntegration;
import io.github.thepoultryman.buckethat.command.global.GlobalCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

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

        if (issue != null && issue <= 0) {
            embedBuilder.setTitle("silly g00se").setColor(new Color(0x850303))
                    .setDescription("1. There cannot be issues with a issue 0 or lower." +
                            "\n2. This means that Bucket-Hat-Bot cannot have an issue " + issue);
        } else if (repository == null && issue == null) {
            embedBuilder = this.getRepositoryStatsEmbed("ThePoultryMan/Bucket-Hat-Bot", requestingName);
        } else if (repository != null && issue == null) {
            GHRepository ghRepository = BucketHat.gitHub.getRepository(repository);
            if (ghRepository != null) {
                embedBuilder = this.getRepositoryStatsEmbed(repository, requestingName);
            } else {
                embedBuilder.setTitle("~~oops, something went wrong~~ Invalid Repository").setDescription(this.getRepositoryFormatDesc());
            }
        } else {
            embedBuilder = this.getEmbedIssueResponse(Objects.requireNonNullElse(repository, "ThePoultryMan/Bucket-Hat-Bot"), issue);
        }

        return embedBuilder.build();
    }

    private EmbedBuilder getRepositoryStatsEmbed(String repository, String requestingName) {
        GitHubIntegration gitHub = BucketHat.gitHub;

        return new EmbedBuilder().setColor(new Color(0x077EE0))
                .setAuthor(gitHub.getRepositoryOwner(repository).getLogin() + "/",
                        String.valueOf(gitHub.getRepositoryOwner(repository).getHtmlUrl()))
                .setTitle(gitHub.getRepository(repository).getName(), String.valueOf(gitHub.getRepository(repository).getHtmlUrl()))
                .addField("Stars", gitHub.getStarCount(repository).toString(), true)
                .addBlankField(true)
                .addField("Watchers", gitHub.getWatcherCount(repository).toString(), true)
                .addField("Pull Requests (Open)", gitHub.getOpenPrCount(repository).toString(), true)
                .addBlankField(true)
                .addField("Issues (Open)", gitHub.getOpenIssueCount(repository).toString(), true)
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
                    embedBuilder.setTitle(ghRepository.getName() + " Issue #" + issue).setColor(new Color(0xD38309))
                            .addField(ghIssue.getTitle(), ghIssue.getBody(), false);
                } else {
                    String link = "...\n[[Read More]](" + ghIssue.getHtmlUrl() + ")";
                    embedBuilder.setTitle(ghRepository.getName() + " Issue #" + issue).setColor(new Color(0xD38309))
                            .addField(ghIssue.getTitle(), ghIssue.getBody().substring(0, 1024 - link.length()) + link, false);
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
        return new EmbedBuilder().setTitle("#" + issue + " not found in " + repository).setColor(new Color(0xC93508))
                .setDescription("Issue #" + issue + " could not be found in " + repository + ". This usually means that there is no" +
                        " issue #" + issue + ".");
    }

    public MessageEmbed getUserEmbed(String username) {
        EmbedBuilder userEmbedBuilder = new EmbedBuilder();
        GitHubIntegration gitHub = BucketHat.gitHub;
        GHOrganization organization = BucketHat.gitHub.getOrganization(username);
        GHUser user = BucketHat.gitHub.getUser(username);

        if (organization != null) {
            return userEmbedBuilder.setColor(new Color(0x077EE0)).setTitle(username)
                    .setThumbnail(String.valueOf(organization.getAvatarUrl()))
                    .addField("Repositories (Public)", gitHub.getOrgPublicRepoCount(organization).toString(), false)
                    .build();
        } else if (user != null) {
            return userEmbedBuilder.setColor(new Color(0x077EE0)).setTitle(username)
                    .setThumbnail(String.valueOf(user.getAvatarUrl()))
                    .setDescription(user.getBio())
                    .addField("Repositories (Public)", gitHub.getPublicRepositoryCount(user).toString(), false)
                    .addField("Following", gitHub.getFollowingCount(user).toString(), false)
                    .addField("Followers", gitHub.getFollowersCount(user).toString(), false)
                    .build();
        } else {
            return userEmbedBuilder.setColor(new Color(0xD38309)).setTitle("No User Found")
                    .setDescription("No user with the username '" + username + "' was found on GitHub").build();
        }
    }
}
