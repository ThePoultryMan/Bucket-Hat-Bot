package io.github.thepoultryman.buckethat.command.guild;

import io.github.thepoultryman.buckethat.BucketHat;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.HashSet;

public class GuildCommandManager {
    private final Logger BH_LOGGER = BucketHat.LOGGER;
    private static final Guild guild = BucketHat.getJda().getGuildById(BucketHat.DOTENV.get("GUILD_ID"));
    private static final Collection<SlashCommandData> commands = new HashSet<>();

    public static void addGuildCommand(SlashCommandData command) {
        commands.add(command);
    }

    public static Collection<SlashCommandData> getGuildCommands() {
        return commands;
    }

    public static void createGuildCommands() {
        guild.updateCommands().addCommands(commands).queue();
    }
}
