package me.krynox.spectral.block;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.entity.SpectralForgeBE;
import me.krynox.spectral.capability.ectohandler.IEctoHandler;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
            if(be != null) {
                if(be.isActive()) {
                    be.withdrawItems((ServerPlayer) pPlayer);
                } else {
                    be.tryInitialiseMultiblock();
                }
            }

        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SpectralForgeBE(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == Registration.SPECTRAL_FORGE_BE.get()
                ? (l,p,s,e) -> SpectralForgeBE.tick(l,p,s, (SpectralForgeBE) e)
                : null;
    }
}
