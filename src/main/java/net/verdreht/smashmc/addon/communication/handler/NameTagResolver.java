package net.verdreht.smashmc.addon.communication.handler;

import com.google.gson.JsonElement;
import net.verdreht.smashmc.addon.communication.ChannelListener;

public class NameTagResolver extends ChannelListener {

    protected NameTagResolver() {
        super("player.update-player-info");
    }

    @Override
    public void handleMessage(String messageEntry, JsonElement serverMessage) {
        System.out.println("Received plugin info (player): " + messageEntry + " (" + serverMessage.toString() + ")");
    }
}
