package net.verdreht.smashmc.addon.communication.handler;

import com.google.gson.JsonElement;
import net.verdreht.smashmc.addon.communication.ChannelListener;

public class EmoteTestChannelListener extends ChannelListener {

    public EmoteTestChannelListener() {
        super("emote_api");
    }

    @Override
    public void handleMessage(String messageEntry, JsonElement serverMessage) {
        System.out.println("Received plugin info (emote_api): " + messageEntry + " (" + serverMessage.toString() + ")");
    }
}
