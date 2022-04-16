package io.github.thepoultryman.buckethat.command.guild.json;

public class BasicCommandJson {
    private final String type;
    private final String name;
    private final String description;
    private final String response;

    // Embed Things
    private final String title;

    public BasicCommandJson(String type, String name, String description, String response, String title) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.response = response;
        // Embed Things
        this.title = title;
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
}
