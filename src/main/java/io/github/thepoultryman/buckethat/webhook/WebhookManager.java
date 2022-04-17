package io.github.thepoultryman.buckethat.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class WebhookManager {
    private final WebhookClient client;
    private final WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();

    public WebhookManager(String url) {
        WebhookClientBuilder builder = new WebhookClientBuilder(url);
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Webhook");
            thread.setDaemon(true);
            return thread;
        });
        builder.setWait(true);
        this.client = builder.build();
    }
    public void customizeAppearance(String username, String url) {
        this.setUsername(username);
        this.setAvatar(url);
    }

    public void setUsername(String username) {
        this.messageBuilder.setUsername(username);
    }

    public void setAvatar(String url) {
        this.messageBuilder.setAvatarUrl(url);
    }

    public void sendMessage(String message, boolean withAppearance) {
        if (!withAppearance)
            this.client.send(message);
        else
            this.client.send(this.messageBuilder.setContent(message).build());
    }
}
