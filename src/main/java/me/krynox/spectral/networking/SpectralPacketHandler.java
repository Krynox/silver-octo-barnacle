package me.krynox.spectral.networking;

import me.krynox.spectral.Spectral;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class SpectralPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            Spectral.resLoc("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    public static void init(){
        int id = 0;
        INSTANCE.registerMessage(id++, SpectralKeybindMessage.class, SpectralKeybindMessage::encoder, SpectralKeybindMessage::decoder, SpectralKeybindMessage::messageConsumer);
    }
}
