package io.github.thepoultryman.buckethat.command.guild;

import com.google.gson.Gson;
import io.github.thepoultryman.buckethat.BucketHat;
import io.github.thepoultryman.buckethat.command.guild.json.BasicCommandJson;
import io.github.thepoultryman.buckethat.command.guild.json.EmbedCommandJson;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.slf4j.Logger;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class GuildCommandReader {
    private static final Logger BH_LOGGER = BucketHat.LOGGER;
    private final String rootDir;

    private final Collection<SlashCommandData> commandCollection = new HashSet<>();

    public GuildCommandReader(String rootDir) {
        this.rootDir = rootDir;
    }

    public void readBasicCommands() {
        File directory = new File(this.rootDir + "\\data\\guild");
        if (directory.exists()) {
            BH_LOGGER.info("Guild Command Directory Exists: Reading guild commands now!");

            for (File command : Objects.requireNonNull(directory.listFiles())) {
                if (command.isFile()) {
                    Gson gson = new Gson();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(command.toString()));
                        BasicCommandJson commandJson = gson.fromJson(reader, BasicCommandJson.class);
                        if (commandJson.getType().equals("basic")) {
                            this.addBasicCommand(commandJson.getName(), commandJson.getDescription());
                            GuildCommandHelper.addBasicCommand(commandJson.getName(), commandJson.getResponse());
                        } else if (commandJson.getType().equals("basic-embed")) {
                            this.addBasicCommand(commandJson.getName(), commandJson.getDescription());
                            GuildCommandHelper.addBasicEmbedCommand(commandJson.getName(), commandJson);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (!command.getName().equals("embeds")) {
                    BH_LOGGER.warn("Unexpected folder in guild command directory!");
                }
            }
        } else {
            BH_LOGGER.warn("Guild Command Directory Does Not Exist: Create the directory now according to the spec." +
                    "\nWill return a single ping command.");

            this.commandCollection.add(Commands.slash("ping", "Returns the ping to the user."));
            GuildCommandHelper.addBasicCommand("ping", "Your current ping is: IDK, lol." +
                    "\nIf you see this it means something went wrong.");
        }
    }

    public void readEmbedCommands() {
        File directory = new File(this.rootDir + "\\data\\guild\\embeds");
        if (directory.exists()) {
            BH_LOGGER.info("Guild Embed Command Directory Exists: Reading guild commands now!");

            for (File command : Objects.requireNonNull(directory.listFiles())) {
                Gson gson = new Gson();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(command.toString()));
                    EmbedCommandJson commandJson = gson.fromJson(reader, EmbedCommandJson.class);
                    this.addBasicCommand(commandJson.getName(), commandJson.getDescription());
                    GuildCommandHelper.addEmbedCommand(commandJson.getName(), commandJson);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            BH_LOGGER.warn("Guild Embed Command Directory Does Not Exist: Create the directory now according to the spec." +
                    "\nNo embed command will be added to the bot automatically.");
        }
    }

    private void addBasicCommand(String name, String description) {
        this.commandCollection.add(Commands.slash(name, description));
    }

    public Collection<SlashCommandData> getCommandCollection() {
        return this.commandCollection;
    }
}
