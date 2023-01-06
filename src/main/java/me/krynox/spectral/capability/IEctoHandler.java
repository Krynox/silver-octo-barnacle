package me.krynox.spectral.capability;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IEctoHandler {

    int get(EctoType type);
    IEctoHandler set(EctoType type, int x);

    default IEctoHandler add(EctoType type, int x) {
        return set(type, get(type));
    }

}
