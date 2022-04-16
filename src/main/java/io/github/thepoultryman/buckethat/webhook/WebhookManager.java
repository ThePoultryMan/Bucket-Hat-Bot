package io.github.thepoultryman.buckethat.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;

public class WebhookManager {
    private final WebhookClient client;

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
}
