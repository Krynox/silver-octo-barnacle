package me.krynox.spectral.magic;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;


public abstract class AbstractBuff {

    public AbstractBuff() {

    }

    public abstract void serverTick(ServerLevel level, ServerPlayer player);
    public abstract void clientTick(ClientLevel level, LocalPlayer player);
}
