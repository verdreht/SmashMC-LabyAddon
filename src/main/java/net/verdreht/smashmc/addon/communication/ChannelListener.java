package net.verdreht.smashmc.addon.communication;

import com.google.gson.JsonElement;
import net.labymod.api.events.ServerMessageEvent;
import net.verdreht.smashmc.addon.SmashAddon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.reflect.ClassPath.*;

public class ChannelListener implements ServerMessageEvent {

    private static final Map<String, JsonElement> channelCache = new HashMap<>();
    private static final Set<ChannelListener> listeners = new HashSet<>();

    private SmashAddon addon;

    private String channelMessageIdentifier;

    /**
     * This constructor must be implemented/overridden in the extending class.
     */
    public ChannelListener() {}

    /**
     * This constructor should be called once in the main class.
     * It is not recommended overriding this method.
     *
     * @param initialize It automatically registers all listeners extending this class in a certain package.
     */
    public ChannelListener(SmashAddon addon, boolean initialize) {
        addon.getApi().getEventManager().register(this);
        this.addon = addon;

        if (initialize) initialize();
    }

    /**
     * This constructor must be called in your extending class.
     *
     * It is not recommended overriding this method.
     */
    protected ChannelListener(String channelMessageIdentifier) {
        this.channelMessageIdentifier = channelMessageIdentifier;
    }

    /**
     * Implements the onServerMessage(String messageKey, JsonElement serverMessage) method from the interface ServerMessageEvent.
     * This method just forwards its parameters to the private execute method
     * which determines searches for the correct channelMessageIdentifier matching the messageKey
     *
     * @param messageKey the name of the retrieved plugin message
     * @param serverMessage the content of the retrieved plugin message
     */
    @Override
    public void onServerMessage(String messageKey, JsonElement serverMessage) {
        if(SmashAddon.getInstance().isDebugMode()) System.out.println("Received plugin info: " + messageKey + " (" + serverMessage.toString() + ")");

        execute(messageKey, serverMessage);
        channelCache.put(messageKey, serverMessage);
    }

    /**
     * Is called by the overridden onServerMessage method from ServerMessageEvent.
     * It searches in the registered listeners list if a listener matches the messageKey
     *
     * @param messageKey the name of the retrieved plugin message
     * @param serverMessage the content of the retrieved plugin message
     */
    private void execute(String messageKey, JsonElement serverMessage) {
        for(ChannelListener listener : listeners) {
            String identifier = listener.getChannelMessageIdentifier();
            if(identifier != null && identifier.equals(messageKey)) {
                handleMessage(messageKey, serverMessage);
                break;
            }
        }
    }

    /**
     * Initializes all listeners located at net.verdreht.smashmc.addon.communication.handler extending this class.
     * This method should only be executed once in runtime.
     *
     * @return returns true if the operation was executed successfully
     */
    public boolean initialize() {
        try {
            String packageName = "net.verdreht.smashmc.addon.communication.handler";
            ClassLoader classLoader = SmashAddon.getInstance().getClass().getClassLoader();
            for(ClassInfo info : from(classLoader).getTopLevelClasses(packageName)) {
                Object obj = Class.forName(info.getName(), true, classLoader).newInstance();
                if(obj instanceof ChannelListener) {
                    registerListener((ChannelListener) obj);
                    System.out.println(SmashAddon.getInstance().getPrefix() + "Registered extending ChannelListener listener: " + obj.getClass().getName().replace(packageName + ".", ""));
                }
            }
            return true;
        } catch(Exception ex) {
            System.err.println(SmashAddon.getInstance().getPrefix() + "Error occurred while registering ChannelListener. Trace output shown below:\n\n”");
            ex.printStackTrace();
        }
        return false;
    }

    protected Map<String, JsonElement> getChannelCache() {
        return channelCache;
    }

    public void registerListener(ChannelListener listener) {
        if (listener != null) listeners.add(listener); else throw new NullPointerException("ChannelListener was null");
    }

    public void handleMessage(String messageEntry, JsonElement serverMessage) {}

    protected String getChannelMessageIdentifier() {
        return channelMessageIdentifier;
    }

    public SmashAddon getAddon() {
        return addon;
    }
}