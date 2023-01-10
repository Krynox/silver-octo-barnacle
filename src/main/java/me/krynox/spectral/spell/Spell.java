package me.krynox.spectral.spell;

import me.krynox.spectral.setup.Registration;
import net.minecraft.Util;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class Spell {
    @Nullable
    private String descriptionId = null;

    public void castServer(ServerPlayer player, ServerLevel level) {

    }

    public void castClient(Player player, ClientLevel level) {

    }

    /**
     * Get the unlocalized name of the spell.
     */
    public String getDescriptionId() {
        if(this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("spell", Registration.SPELLS_REEGISTRY.get().getKey(this));
        }
        return this.descriptionId;
    }
}
