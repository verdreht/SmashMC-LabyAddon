package net.verdreht.smashmc.addon.listener;

import net.labymod.api.events.PluginMessageEvent;
import net.minecraft.network.PacketBuffer;

public class PluginMessageListener implements PluginMessageEvent {

    @Override
    public void receiveMessage(String s, PacketBuffer packetBuffer) {
        System.out.println(s);
    }
}
