package me.krynox.spectral.capability;

import me.krynox.spectral.capability.ectohandler.IEctoHandler;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class SpectralCapabilities {
    public static final Capability<IEctoHandler> ECTO_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});
}
