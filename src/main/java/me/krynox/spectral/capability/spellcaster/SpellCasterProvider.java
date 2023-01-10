package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SpellCasterProvider implements ICapabilitySerializable<CompoundTag> {
    private final LazyOptional<ISpellCaster> CAP = LazyOptional.of(SpellCasterImpl::new);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SpectralCapabilities.SPELL_CASTER) {
            return CAP.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        Optional<ISpellCaster> resolvedCap = CAP.resolve();

        if(resolvedCap.isEmpty()) {
            Spectral.LOGGER.error("Tried to serialize ISpellCaster capability, but it was empty.");
            return new CompoundTag();
        }

        return resolvedCap.get().serialize();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        Optional<ISpellCaster> resolvedCap = CAP.resolve();

        if(resolvedCap.isEmpty()) {
            Spectral.LOGGER.error("Tried to deserialize ISpellCaster capability, but it was empty.");
            return;
        }

        resolvedCap.get().deserialize(tag);

    }
}
