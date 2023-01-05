package me.krynox.spectral.block.entity;

import me.krynox.spectral.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SpectralForgeBE extends BlockEntity {
    public SpectralForgeBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.SPECTRAL_FORGE_BE.get(), pPos, pBlockState);
    }
}
