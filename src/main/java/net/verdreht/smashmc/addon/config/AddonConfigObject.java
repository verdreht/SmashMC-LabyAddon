package net.verdreht.smashmc.addon.config;

public class AddonConfigObject {

    private final AddonConfigExtended addonConfig;
    private final Object result;

    public AddonConfigObject(AddonConfigExtended config, Object result) {
        this.addonConfig = config;
        this.result = result;
    }

    @Override
    public String toString() {
        return result.toString();
    }

    public String getString() {
        return toString();
    }

    public int getInteger() {
        return Integer.parseInt(result.toString());
    }

    public double getDouble() {
        return Double.parseDouble(result.toString());
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(result.toString());
    }

    public AddonConfigExtended getAddonConfig() {
        return addonConfig;
    }

    public Object getObject() {
        return result;
    }
}
