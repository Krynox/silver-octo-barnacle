package me.krynox.spectral.capability.combatdata;

import me.krynox.spectral.capability.SpectralCapabilities;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class CombatDataProvider implements ICapabilityProvider {
    private final LazyOptional<ICombatData> CAP = LazyOptional.of(CombatDataImpl::new);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SpectralCapabilities.COMBAT_DATA) {
            return CAP.cast();
        }
        return LazyOptional.empty();
    }
}
