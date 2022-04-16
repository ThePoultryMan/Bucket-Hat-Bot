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

    public static MessageEmbed getBasicEmbedCommandResponse(String commandName) {
        BasicCommandJson commandJson = basicEmbedCommandMap.get(commandName);

        return new EmbedBuilder().setTitle(commandJson.getTitle())
                .addField("", commandJson.getResponse(), false)
                .setFooter("Bucket Hat").build();
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

    public static MessageEmbed getEmbedCommandResponse(String commandName) {
        EmbedCommandJson commandJson = embedCommandMap.get(commandName);
        EmbedBuilder builder = new EmbedBuilder().setTitle(commandJson.getTitle());

        for (int i = 0; i < commandJson.getFields().size(); ++i) {
            EmbedCommandJson.EmbedField embedField = commandJson.getFields().get(i);
            builder.addField(embedField.getHeader(), embedField.getBody(), false);
        }

        return builder.build();
    }

    public static boolean isEmbedCommand(String commandName) {
        return embedCommandMap.containsKey(commandName);
    }
}
