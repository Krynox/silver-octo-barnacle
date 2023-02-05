package me.krynox.spectral.block.entity;

import me.krynox.spectral.content.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SoulMirrorBE extends BlockEntity {
    public SoulMirrorBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.SOUL_MIRROR_BE.get(), pPos, pBlockState);
    }
}
