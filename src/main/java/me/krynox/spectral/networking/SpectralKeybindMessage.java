package me.krynox.spectral.networking;

import me.krynox.spectral.client.keybind.KeybindHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpectralKeybindMessage {
    private final SpectralKeybindAction action;
    public SpectralKeybindMessage(SpectralKeybindAction action) {
        this.action = action;
    }

    public void encoder(FriendlyByteBuf buffer) {
        buffer.writeInt(action.getId());
    }

    public static SpectralKeybindMessage decoder(FriendlyByteBuf buffer) {
        return new SpectralKeybindMessage(SpectralKeybindAction.fromId(buffer.readInt()).orElseThrow());
    }

    public void messageConsumer(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            KeybindHandler.handleSpectralKeybind(ctx.get().getSender(), action, false);
        });
        ctx.get().setPacketHandled(true);
    }
}
