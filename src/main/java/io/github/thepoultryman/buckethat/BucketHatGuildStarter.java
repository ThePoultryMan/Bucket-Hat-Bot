package io.github.thepoultryman.buckethat;

import io.github.thepoultryman.buckethat.command.guild.GuildCommandManager;
import io.github.thepoultryman.buckethat.command.guild.GuildCommandReader;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BucketHatGuildStarter extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        if (BucketHat.getJda().getGuildById("903793904401395774") != null) {
            // TODO: Add this to the config system. (Create a config system)
            GuildCommandReader commandReader = new GuildCommandReader(BucketHat.DOTENV.get("ROOT_DIR"));

            commandReader.readBasicCommands();
            commandReader.readEmbedCommands();

            GuildCommandManager.createGuildCommands();
        } else {
            BucketHat.LOGGER.warn("[Substantial Error]: It appears that the specified GuildId doesn't match with an existing guild.");
        }
    }
}
