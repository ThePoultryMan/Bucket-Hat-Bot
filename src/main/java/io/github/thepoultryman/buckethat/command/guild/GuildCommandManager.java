package io.github.thepoultryman.buckethat.command.guild;

import io.github.thepoultryman.buckethat.BucketHat;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.HashSet;

public class GuildCommandManager {
    private final Logger BH_LOGGER = BucketHat.LOGGER;
    private final Guild guild;
    Collection<SlashCommandData> commands = new HashSet<>();

    public GuildCommandManager(Guild guild) {
        this.guild = guild;
    }

    /**
     * <p>Adds the existing (slash) commands retrieved from Discord back
     * into the command collection, so they are not discarded when this
     * method is run.</p>
     * <p>If someone wants to remove a command from the retrieval list,
     * you should call {@link }</p>
     */
    public void addAllCommands() {
        guild.updateCommands().addCommands(commands).queue();
    }

    /**
     * <p>Adds the existing (slash) commands retrieved from Discord back
     * into the command collection, so they are not discarded when this
     * method is run.</p>
     * <p>If someone wants to remove a command from the retrieval list,
     * you should call {@link }</p>
     */
    public void addAllCommands(Collection<SlashCommandData> commands) {
        guild.updateCommands().addCommands(commands).queue();
    }

    /**
     * <p><b>Permanently</b> deletes all of the guild commands the have
     * been created. The only way of getting the commands back is to
     * recreate them again.</p>
     * <p>This should only be run if it is needed, or for testing purposes.</p>
     */
    public void clearAllCommands() {
        commands.clear();
        guild.updateCommands().queue();
    }
}
