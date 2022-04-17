package io.github.thepoultryman.buckethat.command.guild;

import io.github.thepoultryman.buckethat.command.guild.json.BasicCommandJson;
import io.github.thepoultryman.buckethat.command.guild.json.EmbedCommandJson;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.HashMap;
import java.util.Map;

public class GuildCommandHelper {
    private static final Map<String, String> basicCommandMap = new HashMap<>();
    private static final Map<String, BasicCommandJson> basicEmbedCommandMap = new HashMap<>();
    private static final Map<String, EmbedCommandJson> embedCommandMap = new HashMap<>();

    public static Map<String, String> getBasicCommandMap() {
        return basicCommandMap;
    }

    public static void addBasicCommand(String commandName, String response) {
        basicCommandMap.put(commandName, response);
    }

    public static String getBasicCommandResponse(String commandName) {
        return basicCommandMap.get(commandName);
    }

    public static boolean isBasicCommand(String commandName) {
        return basicCommandMap.containsKey(commandName);
    }

    public static Map<String, BasicCommandJson> getBasicEmbedCommandMap() {
        return basicEmbedCommandMap;
    }

    public static void addBasicEmbedCommand(String commandName, BasicCommandJson commandJson) {
        basicEmbedCommandMap.put(commandName, commandJson);
    }

    /**
     * Will create a {@link MessageEmbed} that has one field with the
     * value specified by the JSON 'response' field. There will be a
     * footer on the embed as well that reads 'Bucket Hat'
     * @param commandName The name of the basic embed command that will
     *                    be used as the key for the {@code basicEmbedCommandMap}.
     * @return A {@link MessageEmbed} that contains a single field and
     * a footer.
     */
    public static MessageEmbed getBasicEmbedCommandResponse(String commandName) {
        BasicCommandJson commandJson = basicEmbedCommandMap.get(commandName);

        return new EmbedBuilder().setTitle(commandJson.getTitle()).setColor(commandJson.getColor())
                .setDescription(commandJson.getResponse()).build();
    }

    public static boolean isBasicEmbedCommand(String commandName) {
        return basicEmbedCommandMap.containsKey(commandName);
    }

    public static Map<String, EmbedCommandJson> getEmbedCommandMap() {
        return embedCommandMap;
    }

    public static void addEmbedCommand(String commandName, EmbedCommandJson commandJson) {
        embedCommandMap.put(commandName, commandJson);
    }

    /**
     * <p>Generates, and returns, a {@link MessageEmbed} based on the JSON
     * file used. The tile of the embed will be set based on the 'title'
     * field in the JSON file. The 'fields' list will be iterated
     * through creating a new field on the {@link MessageEmbed} on
     * each iteration.</p>
     *
     * @param commandName The name of the command that will be used as
     *                    the key for the {@code embedCommandMap}.
     * @return A {@link MessageEmbed} with the added fields and the
     * other information from the JSON file.
     */
    public static MessageEmbed getEmbedCommandResponse(String commandName) {
        EmbedCommandJson commandJson = embedCommandMap.get(commandName);
        EmbedBuilder builder = new EmbedBuilder().setTitle(commandJson.getTitle());

        for (int i = 0; i < commandJson.getFields().size(); ++i) {
            EmbedCommandJson.EmbedField embedField = commandJson.getFields().get(i);
            builder.addField(embedField.getHeader(), embedField.getBody(), false); // TODO: Set inline based on JSON file.
        }

        return builder.build();
    }

    public static boolean isEmbedCommand(String commandName) {
        return embedCommandMap.containsKey(commandName);
    }
}
