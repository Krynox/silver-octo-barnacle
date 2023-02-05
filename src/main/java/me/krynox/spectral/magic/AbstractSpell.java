package me.krynox.spectral.magic;

import me.krynox.spectral.content.Registration;
import net.minecraft.Util;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSpell {
    @Nullable
    private String descriptionId = null;

    public abstract void castServer(ServerPlayer player, ServerLevel level);

    public abstract void castClient(LocalPlayer player, ClientLevel level);

    /**
     * Get the unlocalized name of the spell.
     */
    public String getDescriptionId() {
        if(this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("spell", Registration.SPELLS_REGISTRY.get().getKey(this));
        }
        return this.descriptionId;
    }

}
