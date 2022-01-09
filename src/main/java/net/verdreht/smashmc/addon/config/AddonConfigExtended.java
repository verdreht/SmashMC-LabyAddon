package net.verdreht.smashmc.addon.config;

import com.google.gson.JsonObject;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.verdreht.smashmc.addon.SmashAddon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AddonConfigExtended {

    private final SmashAddon instance;
    private final Map<String, Object> configMap = new HashMap<>();

    private final JsonObject configObject;

    private List<SettingsElement> subSettings;

    public AddonConfigExtended(SmashAddon instance, JsonObject configObject) {
        this.configObject = configObject;
        this.instance = instance;
        initialize();
    }

    public void setConfigList(List<SettingsElement> subSettings) {
        this.subSettings = subSettings;
    }

    protected void apply(String identifier, Object value) {
        configMap.put(identifier, value);
    }

    public void applySettings(String name, String identifier, Material material) {
        if(!configMap.containsKey(identifier)) return;
        Object value = configMap.get(identifier);

        if(value instanceof Boolean) {
            subSettings.add(new BooleanElement(name, getInstance(), new ControlElement.IconData(material), identifier, Boolean.parseBoolean(value.toString())));
        } else if(value instanceof String) {
            subSettings.add(new StringElement(name, getInstance(), new ControlElement.IconData(material), identifier, value.toString()));
        }
    }

    protected Object getOrDefault(String identifier, Object value) {

        if(value instanceof String) {
            return configObject.has(identifier) ? instance.getConfig().get(identifier).getAsString() : value;
        } else if(value instanceof Integer) {
            return configObject.has(identifier) ? instance.getConfig().get(identifier).getAsInt() : value;
        } else if(value instanceof Double) {
            return configObject.has(identifier) ? instance.getConfig().get(identifier).getAsDouble() : value;
        } else if(value instanceof Boolean) {
            return configObject.has(identifier) ? instance.getConfig().get(identifier).getAsBoolean() : value;
        } else {
            throw new IllegalArgumentException();
        }

    }


    public AddonConfigObject get(String entry) {
        return new AddonConfigObject(this, configMap.getOrDefault(entry, null));
    }

    public SmashAddon getInstance() {
        return instance;
    }

    public abstract void initialize();

    public List<SettingsElement> getSubSettings() {
        return subSettings;
    }
}
