package io.github.thepoultryman.buckethat;

import org.kohsuke.github.*;

import java.io.IOException;

public class GitHubIntegration {
    private final GitHub github = new GitHubBuilder().withOAuthToken(BucketHat.DOTENV.get("GITHUB_TOKEN")).build();

    public GitHubIntegration() throws IOException {}

    public GitHub getGithub() {
        return this.github;
    }

    public GHRepository getRepository(String name) {
        if (name.split("/").length != 2)
            return null;

        try {
            return github.getRepository(name);
        } catch (IOException e) {
            return null;
        }
    }

    public GHUser getRepositoryOwner(String repository) {
        try {
            return github.getRepository(repository).getOwner();
        } catch (IOException e) {
            return null;
        }
    }

    public GHIssue getIssue(int issueNumber) {
        try {
            return github.getRepository("ThePoultryMan/Bucket-Hat-Bot").getIssue(issueNumber);
        } catch (IOException e) {
            return null;
        }
    }

    public GHIssue getIssue(String repository, int issueNumber) {
        try {
            return github.getRepository(repository).getIssue(issueNumber);
        } catch (IOException e) {
            return null;
        }
    }

    public Integer getOpenIssueCount(String repository) {
        try {
            return github.getRepository(repository).getOpenIssueCount();
        } catch (IOException e) {
            return 0;
        }
    }

    public Integer getStarCount(String repository) {
        try {
            return github.getRepository(repository).getStargazersCount();
        } catch (IOException e) {
            return 0;
        }
    }

    public Integer getWatcherCount(String repository) {
        try {
            return github.getRepository(repository).getWatchersCount();
        } catch (IOException e) {
            return 0;
        }
    }
}
