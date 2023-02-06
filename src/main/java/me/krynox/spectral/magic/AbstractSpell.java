package me.krynox.spectral.magic;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.content.Registration;
import net.minecraft.Util;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;

public abstract class AbstractSpell {
    public static AbstractSpell SimpleRaycast(float len, int targetCap, Consumer<Entity> effect) {
        return new AbstractSpell(false) {
            @Override
            public void castServer(ServerPlayer player, ServerLevel level, float partialTicks) {
                List<Entity> targets = SpellUtils
                        .entitiesInRay(level, player, partialTicks, player.getLookAngle(), len, ($) -> true);

                targets.subList(0, targetCap-1).forEach(effect);
            }

            @Override
            public void castClient(LocalPlayer player, ClientLevel level, float partialTicks) {

            }
        };
    }

    @Nullable //populated lazily by getDescriptionId(), because it depends on the registry being loaded
    private String descriptionId = null;

    // Whether the spell is continuous, in which case the cast methods are called every tick while the button is held,
    // or not, in which case they fire once when the button is *released*.
    private final boolean CONTINUOUS;

    public AbstractSpell(boolean continuous) {
        this.CONTINUOUS = continuous;
    }

    public abstract void castServer(ServerPlayer player, ServerLevel level, float partialTicks);

    public abstract void castClient(LocalPlayer player, ClientLevel level, float partialTicks);

    /**
     * Get the unlocalized name of the spell.
     */
    public String getDescriptionId() {
        if(this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("spell", Registration.SPELLS_REGISTRY.get().getKey(this));
        }
        return this.descriptionId;
    }

    public boolean isContinuous() {
        return CONTINUOUS;
    }

}
