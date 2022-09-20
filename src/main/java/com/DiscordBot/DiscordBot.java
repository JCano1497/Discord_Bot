package com.DiscordBot;

import com.DiscordBot.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private final Dotenv config;
    private final ShardManager shardManager;

    public DiscordBot() throws LoginException {
        config = Dotenv.configure().ignoreIfMissing().load();
        // shard manager allows it to run on multiple servers
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("Something"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();
        shardManager.addEventListener(new EventListener());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
    public Dotenv getConfig(){
        return config;
    }

    public static void main(String[] args){
        try {
            DiscordBot bot = new DiscordBot();
        } catch (LoginException e){
            System.out.println("Error");
        }
    }
}
