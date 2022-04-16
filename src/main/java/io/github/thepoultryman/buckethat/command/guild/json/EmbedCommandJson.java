package io.github.thepoultryman.buckethat.command.guild.json;

import java.util.List;

public class EmbedCommandJson {
    private final String name;
    private final String description;
    private final String title;
    private final List<EmbedField> fields;

    public EmbedCommandJson(String name, String description, String title, List<EmbedField> fields) {
        this.name = name;
        this.description = description;
        this.title = title;
        this.fields = fields;
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
}
