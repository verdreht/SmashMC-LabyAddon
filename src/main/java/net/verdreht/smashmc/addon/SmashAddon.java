package net.verdreht.smashmc.addon;


import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.verdreht.smashmc.addon.communication.ChannelListener;
import net.verdreht.smashmc.addon.listener.UserJoinListener;
import net.verdreht.smashmc.addon.support.SmashMC;
import net.verdreht.smashmc.addon.support.config.SmashConfig;

import java.util.List;

public class SmashAddon extends LabyModAddon {

    private final String PREFIX = "§8● §aSmashMC §8➥ §7";
    private final SmashMC server = new SmashMC("SmashMC", "SmashMC.eu", "CokeJoke.de");

    private ChannelListener channelListener;
    private SmashConfig config;

    private static SmashAddon instance;

    @Override
    public void onEnable() {
        getApi().registerServerSupport(this, server);
        getApi().registerForgeListener( new UserJoinListener());
        instance = this;

        channelListener = new ChannelListener();
        getApi().getEventManager().register(channelListener);

        System.out.println(PREFIX + "Addon has been successfully activated§8.");
    }

    @Override
    public void loadConfig() {
        config = new SmashConfig(this, getConfig());
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        config.setConfigList(list);
        config.applySettings("Addon activated", "addonActivated", Material.REDSTONE_COMPARATOR);
        config.applySettings("Player stats", "displayPlayerStats", Material.PAPER);
    }

    public String getPrefix() {
        return PREFIX;
    }

    public SmashMC getServer() {
        return server;
    }

    public static SmashAddon getInstance() {
        return instance;
    }

    public ChannelListener getChannelListener() {
        return channelListener;
    }
}
