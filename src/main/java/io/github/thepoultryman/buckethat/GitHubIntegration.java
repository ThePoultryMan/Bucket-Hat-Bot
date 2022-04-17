package io.github.thepoultryman.buckethat;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class GitHubIntegration {
    private final GitHub github = new GitHubBuilder().withOAuthToken(BucketHat.DOTENV.get("GITHUB_TOKEN")).build();

    public GitHubIntegration() throws IOException {}

    public GitHub getGithub() {
        return this.github;
    }

    public GHIssue getIssue(int i) {
        try {
            return github.getRepository("ThePoultryMan/Bucket-Hat-Bot").getIssue(i);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
