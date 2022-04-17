package io.github.thepoultryman.buckethat.command.guild.json;

import io.github.thepoultryman.buckethat.BucketHat;

import java.awt.*;

public class BasicCommandJson {
    private final String type;
    private final String name;
    private final String description;
    private final String response;

    // Embed Things
    private final String title;
    private final String color;

    public BasicCommandJson(String type, String name, String description, String response, String title, String color) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.response = response;
        // Embed Things
        this.title = title;
        this.color = color;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getResponse() {
        return this.response;
    }

    //Embed Things
    public String getTitle() {
        return this.title;
    }

    public Color getColor() {
        return switch (color) {
            case "red" -> new Color(0x850303);
            case "orange" -> new Color(0xD38309);
            case "yellow" -> new Color(0xC2A507);
            case "green" -> new Color(0x3CB40F);
            case "turquoise" -> new Color(0x0CC779);
            case "light_blue" -> new Color(0x09B2DA);
            case "blue" -> new Color(0x077EE0);
            case "dark_blue" -> new Color(0x083DC9);
            case "purple" -> new Color(0x6006CC);
            case "pink" -> new Color(0xB10AC0);
            case "hot_pink" -> new Color(0xAD098E);
            default -> sendColorWarning();
        };
    }

    private Color sendColorWarning() {
        BucketHat.LOGGER.warn("An invalid color was set for a basic embed command. Invalid Color: '" + color + "'");
        return new Color(0x077EE0);
    }
}
