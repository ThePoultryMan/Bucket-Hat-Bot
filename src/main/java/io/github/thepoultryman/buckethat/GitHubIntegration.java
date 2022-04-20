package io.github.thepoultryman.buckethat;

import org.kohsuke.github.*;

import java.io.IOException;

public class GitHubIntegration {
    private final GitHub github = new GitHubBuilder().withOAuthToken(BucketHat.DOTENV.get("GITHUB_TOKEN")).build();

    public GitHubIntegration() throws IOException {}

    public GitHub getGithub() {
        return this.github;
    }

    public GHUser getUser(String username) {
        try {
            return github.getUser(username);
        } catch (IOException e) {
            return null;
        }
    }

    public Integer getPublicRepositoryCount(GHUser user) {
        try {
            return user.getPublicRepoCount();
        } catch (IOException e) {
            return 0;
        }
    }

    public Integer getFollowingCount(GHUser user) {
        try {
            return user.getFollowingCount();
        } catch (IOException e) {
            return 0;
        }
    }

    public Integer getFollowersCount(GHUser user) {
        try {
            return user.getFollowersCount();
        } catch (IOException e) {
            return 0;
        }
    }

    public GHOrganization getOrganization(String name) {
        try {
            return github.getOrganization(name);
        } catch (IOException e) {
            return null;
        }
    }

    public Integer getOrgPublicRepoCount(GHOrganization organization) {
        try {
            return organization.getPublicRepoCount();
        } catch (IOException e) {
            return 0;
        }
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

    public Integer getOpenPrCount(String repository) {
        try {
            return github.getRepository(repository).getPullRequests(GHIssueState.OPEN).size();
        } catch (IOException e)
        {
            return 0;
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
            return github.getRepository(repository).getSubscribersCount();
        } catch (IOException e) {
            return 0;
        }
    }
}
