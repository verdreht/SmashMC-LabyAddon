package net.verdreht.smashmc.addon.communication;

import com.google.gson.JsonElement;
import net.labymod.api.events.ServerMessageEvent;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChannelListener implements ServerMessageEvent {

    private static final Map<String, JsonElement> channelCache = new HashMap<>();
    private static final Set<ChannelListener> listeners = new HashSet<>();

    private String channelMessageIdentifier;

    public ChannelListener() {}

    protected ChannelListener(String channelMessageIdentifier) {
        this.channelMessageIdentifier = channelMessageIdentifier;
    }

    @Override
    public void onServerMessage(String messageKey, JsonElement serverMessage) {
        System.out.println("Received plugin info: " + messageKey + " (" + serverMessage.toString() + ")");

        decide(messageKey, serverMessage);
        channelCache.put(messageKey, serverMessage);
    }

    protected Map<String, JsonElement> getChannelCache() {
        return channelCache;
    }

    public void registerListener(ChannelListener listener) {
        listeners.add(listener);
    }

    private void decide(String messageKey, JsonElement serverMessage) {
        for(ChannelListener listener : listeners) {
            String identifier = listener.getChannelMessageIdentifier();
            if(identifier != null && identifier.equals(messageKey)) {
                handleMessage(messageKey, serverMessage);
                break;
            }
        }
    }

    public void handleMessage(String messageEntry, JsonElement serverMessage) {}

    protected String getChannelMessageIdentifier() {
        return channelMessageIdentifier;
    }
}