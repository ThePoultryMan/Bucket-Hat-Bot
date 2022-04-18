package io.github.thepoultryman.buckethat;

import io.github.thepoultryman.buckethat.command.CommandHelper;
import io.github.thepoultryman.buckethat.command.global.GlobalCommandHelper;
import io.github.thepoultryman.buckethat.command.global.commands.GitHubCommand;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

public class BucketHatGlobalStarter extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        GitHubCommand gitHubCommand = new GitHubCommand("github", "Returns the user GitHub stuff");
        gitHubCommand.addSubcommands(new SubcommandData("repo",
                        "Gets repository and issue information. Will default to the Bucket Hat repository.")
                .addOptions(new OptionData(OptionType.STRING, "repository",
                        "The repository to look for on GitHub."),
                new OptionData(OptionType.INTEGER, "issue",
                        "The issue in a repository that will be retrieved. A repository *must* be specified.")),
                new SubcommandData("user", "Gets information about the specified user.")
                        .addOption(OptionType.STRING, "name", "The name of an user or an organization on GitHub.", true));

        GlobalCommandHelper.addCommand(gitHubCommand);
        CommandHelper.addCommandData(gitHubCommand.getCommandData());
        CommandHelper.setGlobalCommandReady(true);
    }
}
