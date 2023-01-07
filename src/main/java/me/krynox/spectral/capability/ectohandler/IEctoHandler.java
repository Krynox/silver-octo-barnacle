package me.krynox.spectral.capability.ectohandler;

import me.krynox.spectral.spell.MagicType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IEctoHandler {

    int get(MagicType type);

    //Must return this.
    IEctoHandler set(MagicType type, int x);

    // Implementations must be serializable to NBT.
    CompoundTag serialize();

    //Implementations must be mutable, and allow for updating all data from an NBT tag.
    void deserialize(CompoundTag tag);

    /////////////////////
    // Default methods //
    /////////////////////

    default IEctoHandler add(MagicType type, int x) {
        return set(type, x + get(type));
    }


}
