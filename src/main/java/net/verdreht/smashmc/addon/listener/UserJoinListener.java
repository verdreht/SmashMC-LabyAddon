package net.verdreht.smashmc.addon.listener;

import net.labymod.api.LabyModAPI;
import net.labymod.user.User;
import net.labymod.utils.ServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.verdreht.smashmc.addon.SmashAddon;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class UserJoinListener {

    private static int onlineMembers = -1;

    @SubscribeEvent
    public void onTick( TickEvent.ClientTickEvent event ) {
        SmashAddon addon = SmashAddon.getInstance();
        LabyModAPI api = addon.getApi();

        ServerData currentServer = api.getCurrentServer();

        if(currentServer != null && Arrays.stream(addon.getServer().getAddressNames()).anyMatch(address -> address.equalsIgnoreCase(currentServer.getIp()))) {

            if(onlineMembers == -1 || api.getUserManager().getUsers().size() > onlineMembers) {
                onlineMembers = api.getUserManager().getUsers().size();
                for (Map.Entry<UUID, User> entry : api.getUserManager().getUsers().entrySet()) {
                    User user = entry.getValue();
                    user.setSubTitleSize(2);
                    user.setSubTitle("§aSmashMC §8● §7Player");
                }
            }
        }

    }

}
