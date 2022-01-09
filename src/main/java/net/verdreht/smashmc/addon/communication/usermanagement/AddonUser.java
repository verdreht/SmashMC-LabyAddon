package net.verdreht.smashmc.addon.communication.usermanagement;

import java.util.UUID;

public class AddonUser {

    private final UUID uuid;
    private final String displayName;
    private final AddonUserRank rank;

    public AddonUser(UUID uuid, String displayName, AddonUserRank rank) {
        this.uuid = uuid;
        this.displayName = displayName;
        this.rank = rank;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public AddonUserRank getRank() {
        return rank;
    }
}
