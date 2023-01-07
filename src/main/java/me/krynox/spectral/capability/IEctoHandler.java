package me.krynox.spectral.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IEctoHandler {

    int get(EctoType type);

    //Must return this.
    IEctoHandler set(EctoType type, int x);

    // Implementations must be serializable to NBT.
    CompoundTag serialize();

    //Implementations must be mutable, and allow for updating all data from an NBT tag.
    void deserialize(CompoundTag tag);

    default IEctoHandler add(EctoType type, int x) {
        return set(type, x + get(type));
    }


}
