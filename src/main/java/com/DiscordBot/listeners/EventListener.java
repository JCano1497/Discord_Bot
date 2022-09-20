package com.DiscordBot.listeners;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user =event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String ChannelMention = event.getChannel().getAsMention();

        String message = user.getAsTag() + " reacted to a message with " + emoji + " in the " + ChannelMention;
        event.getChannel().sendMessage(message).queue();
    }

    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
        User user = event.getUser();
        String message = "**" + user.getAsTag()+ " updated their online status";
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("ping")){
            event.getChannel().sendMessage("Pong").queue();
        }
        if (message.contains("stupid")){
            event.getChannel().sendMessage("why must you speak").queue();
        }
    }
}
