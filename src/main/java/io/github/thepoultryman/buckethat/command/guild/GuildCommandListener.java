package io.github.thepoultryman.buckethat.command.guild;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildCommandListener extends ListenerAdapter {

    /**
     * <p>When a slash command is made this method will be run. Using
     * the slash command event provided by the super method, and Discord,
     * the name of the command will be assigned to the {@code commandName}
     * variable. This variable is then used with {@link GuildCommandHelper}
     * to check what type of command was used. Once the type has been
     * determined the appropriate method from {@link GuildCommandHelper}
     * will be run so the correct reply can be sent to Discord.</p>
     * <p>The command is checked by seeing if it is in any of the maps
     * in {@link GuildCommandHelper}. If a command is not in the correct
     * map, the bot will not respond, and Discord will provide an
     * ephemeral message to the user of the command that reads, "The
     * application did not respond"</p>
     * @param event A slash command event passed by JDA, and Discord,
     *              that is used to respond to a slash command.
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        if (GuildCommandHelper.isBasicCommand(commandName)) {
            event.reply(GuildCommandHelper.getBasicCommandResponse(commandName)).queue();
        } else if (GuildCommandHelper.isBasicEmbedCommand(commandName)) {
            event.replyEmbeds(GuildCommandHelper.getBasicEmbedCommandResponse(commandName, event.getUser().getName())).queue();
        } else if (GuildCommandHelper.isEmbedCommand(commandName)) {
            event.replyEmbeds(GuildCommandHelper.getEmbedCommandResponse(commandName)).queue();
        }
    }
}
