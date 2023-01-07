package me.krynox.spectral.block;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.entity.SpectralForgeBE;
import me.krynox.spectral.capability.ectohandler.IEctoHandler;
import me.krynox.spectral.capability.SpectralCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import static me.krynox.spectral.spell.MagicType.FIRE;

public class SpectralForge extends Block implements EntityBlock {
    public SpectralForge() {
        super(Properties.of(Material.STONE));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide) {
            SpectralForgeBE be = (SpectralForgeBE) pLevel.getBlockEntity(pPos);
            LazyOptional<IEctoHandler> cap = be.getCapability(SpectralCapabilities.ECTO_HANDLER);
            cap.ifPresent((ecto) -> {
                Spectral.LOGGER.info(Integer.toString(ecto.add(FIRE, 10).get(FIRE)));
                be.setChanged();

            });
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SpectralForgeBE(pPos, pState);
    }
}
