package me.krynox.spectral.capability.combatdata;

import me.krynox.spectral.magic.BuffStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public interface ICombatData {
    List<BuffStack> getBuffs();

    default void addBuff(BuffStack buff) {
        getBuffs().add(buff);
    }

    default void removeBuffs(Predicate<BuffStack> filter) {
        getBuffs().removeIf(filter);
    }

    default void tickAllBuffs(Level level, Player player) {
        if(level instanceof ClientLevel && player instanceof LocalPlayer) {
            for(BuffStack b : getBuffs()) {
                b.getBuff().clientTick((ClientLevel) level, (LocalPlayer) player);
            }
        } else if(level instanceof ServerLevel && player instanceof ServerPlayer) {
            Iterator<BuffStack> buffsIterator = getBuffs().iterator();
            while(buffsIterator.hasNext()) {
                BuffStack b = buffsIterator.next();
                b.getBuff().serverTick((ServerLevel) level, (ServerPlayer) player);
                b.decTicksRemaining();
                if(b.getTicksRemaining() <= 0) buffsIterator.remove();
            }
        }
    }
}
