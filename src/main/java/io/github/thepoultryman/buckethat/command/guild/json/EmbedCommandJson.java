package io.github.thepoultryman.buckethat.command.guild.json;

import io.github.thepoultryman.buckethat.BucketHat;

import java.awt.*;
import java.util.List;

public class EmbedCommandJson {
    private final String name;
    private final String description;
    private final String title;
    private final List<EmbedField> fields;
    private final String color;

    public EmbedCommandJson(String name, String description, String title, List<EmbedField> fields, String color) {
        this.name = name;
        this.description = description;
        this.title = title;
        this.fields = fields;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTitle() {
        return this.title;
    }

    public List<EmbedField> getFields() {
        return this.fields;
    }

    public static class EmbedField {
        private final String header;
        private final String body;

        private EmbedField(String header, String body) {
            this.header = header;
            this.body = body;
        }

        public String getHeader() {
            return this.header;
        }

        public String getBody() {
            return this.body;
        }
    }

    public Color getColor() {
        return switch (color) {
            case "red" -> new Color(0x850303);
            case "orange" -> new Color(0xD38309);
            case "yellow" -> new Color(0xDCBF09);
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
