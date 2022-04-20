package io.github.thepoultryman.buckethat;

import io.github.thepoultryman.buckethat.command.global.GlobalCommandHelper;
import io.github.thepoultryman.buckethat.command.global.GlobalCommandManager;
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

        GitHubCommand gitHubCommand = new GitHubCommand("github", "Returns the user GitHub information about a repository or user.");
        gitHubCommand.addSubcommands(new SubcommandData("repo",
                        "Gets repository and issue information. Will default to the Bucket Hat repository.")
                .addOptions(new OptionData(OptionType.STRING, "repository",
                        "The repository to look for on GitHub. Should be in the user/repo format (organization/repo)."),
                new OptionData(OptionType.INTEGER, "issue",
                        "The issue in a repository that will be retrieved. Defaults to the Bucket Hat Repository.")),
                new SubcommandData("user", "Gets information about the specified user, or organization.")
                        .addOption(OptionType.STRING, "name", "The name of an user or an organization on GitHub.", true));

        GlobalCommandHelper.addCommand(gitHubCommand);
        GlobalCommandManager.addGlobalCommand(gitHubCommand.getCommandData());

        //GlobalCommandManager.createGlobalCommands(); // Should be commented out unless a new global command is added, or if an existing one changes.
    }
}
