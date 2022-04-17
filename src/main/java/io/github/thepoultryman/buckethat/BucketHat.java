package io.github.thepoultryman.buckethat;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.thepoultryman.buckethat.command.global.GlobalCommandListener;
import io.github.thepoultryman.buckethat.command.guild.GuildCommandListener;
import io.github.thepoultryman.buckethat.webhook.WebhookManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class BucketHat {
    public static final Logger LOGGER = LoggerFactory.getLogger("BucketHat");
    public static final Dotenv DOTENV = Dotenv.load();
    private static JDA jda;
    public static GitHubIntegration gitHub;

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault(DOTENV.get("BOT_TOKEN")).build();
        BucketHat.jda = jda;
        jda.addEventListener(new BucketHatGlobalStarter());
        jda.addEventListener(new BucketHatGuildStarter());
        jda.addEventListener(new GlobalCommandListener());
        jda.addEventListener(new GuildCommandListener());

        WebhookManager webhookManager = new WebhookManager("https://discord.com/api/webhooks/" +
                "964999529189503066/oU-5LUK5E2waOVOTXKNYwPretuv0UkL1BAztTccl8KGHKarkCIv5zWsAMtpjbdK7-JAK");

        try {
            gitHub = new GitHubIntegration();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JDA getJda() {
        return BucketHat.jda;
    }
}
