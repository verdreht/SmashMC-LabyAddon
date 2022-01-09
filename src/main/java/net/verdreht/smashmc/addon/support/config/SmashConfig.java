package net.verdreht.smashmc.addon.support.config;

import com.google.gson.JsonObject;
import net.verdreht.smashmc.addon.SmashAddon;
import net.verdreht.smashmc.addon.config.AddonConfigExtended;

public class SmashConfig extends AddonConfigExtended {

    public SmashConfig(SmashAddon instance, JsonObject jsonObject) {
        super(instance, jsonObject);
    }

    @Override
    public void initialize() {
        apply("addonActivated", getOrDefault("addonActivated", true));
        apply("displayPlayerStats", getOrDefault("displayPlayerStats", true));
    }
}
