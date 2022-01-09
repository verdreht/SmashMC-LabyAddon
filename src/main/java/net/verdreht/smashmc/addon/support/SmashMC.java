package net.verdreht.smashmc.addon.support;

import net.labymod.api.events.TabListEvent;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;
import net.verdreht.smashmc.addon.SmashAddon;
import net.verdreht.smashmc.addon.support.config.SmashConfig;

import java.util.List;

public class SmashMC extends Server {

    public SmashMC(String name, String... addressNames) {
        super(name, addressNames);
    }

    @Override
    public void onJoin(ServerData serverData) {
        getBindedAddon().getApi().displayMessageInChat(SmashAddon.getInstance().getPrefix() + "§aVielen Dank, dass du unser §eLabyModAddon §ainstalliert hast.");
    }

    @Override
    public ChatDisplayAction handleChatMessage(String s, String s1) {
        return null;
    }

    @Override
    public void handlePluginMessage(String s, PacketBuffer packetBuffer) {

    }

    @Override
    public void handleTabInfoMessage(TabListEvent.Type type, String s, String s1) {

    }

    @Override
    public void fillSubSettings(List<SettingsElement> list) {

    }
}
