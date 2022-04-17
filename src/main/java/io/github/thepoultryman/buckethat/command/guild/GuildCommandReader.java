package io.github.thepoultryman.buckethat.command.guild;

import com.google.gson.Gson;
import io.github.thepoultryman.buckethat.BucketHat;
import io.github.thepoultryman.buckethat.command.CommandHelper;
import io.github.thepoultryman.buckethat.command.guild.json.BasicCommandJson;
import io.github.thepoultryman.buckethat.command.guild.json.EmbedCommandJson;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.slf4j.Logger;

import java.io.*;
import java.util.Objects;

public class GuildCommandReader {
    private static final Logger BH_LOGGER = BucketHat.LOGGER;
    private final String rootDir;

    public GuildCommandReader(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * <p>Reads the guild command directory for guild commands to create.
     * If the directory does not exist then a warning will be logged, and
     * a fake ping command will be added. The directory will be read and
     * each file will be read for the basic command data that it requires.</p>
     * <p>Both Basic and Basic-Embed commands are read from this directory.
     * Depending on the type of command, that is determined from the
     * associated field in the JSON file, it will be added to a different
     * map within {@link GuildCommandHelper}.</p>
     * <p>When the directory is being iterated through, if there is an
     * unexpected directory, a warning will be passed into the log.</p>
     * <br>
     * <b>Expected Directories:</b>
     * <ul>
     *     <li>embeds</li>
     * </ul>
     */
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

            CommandHelper.addCommandData(Commands.slash("ping", "Returns the ping to the user."));
            GuildCommandHelper.addBasicCommand("ping", "Your current ping is: IDK, lol." +
                    "\nIf you see this it means something went wrong.");
        }
    }

    /**
     * <p>Reads the embed command directory to create embed commands.
     * If the directory does not exist, a warning will be logged. If
     * the directory does exist, it will be iterated through to create
     * embed commands.</p>
     */
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
        CommandHelper.addCommandData(Commands.slash(name, description));
    }
}
