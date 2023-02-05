package me.krynox.spectral.block;

import me.krynox.spectral.block.entity.SoulMirrorBE;
import me.krynox.spectral.inventory.menu.SoulMirrorMenu;
import me.krynox.spectral.util.LocalisationHelper;
import me.krynox.spectral.util.LocalisedTextCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class SoulMirror extends Block implements EntityBlock {
    public SoulMirror() {
        super(Properties.of(Material.STONE));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if(!pLevel.isClientSide) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if(be instanceof SoulMirrorBE) {
                MenuProvider menuProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return LocalisationHelper.newUnlocName(LocalisedTextCategory.SCREEN, "soul_mirror");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                        return new SoulMirrorMenu(pContainerId, pPlayerInventory, pPlayer, pPos);
                    }
                };

                NetworkHooks.openScreen((ServerPlayer) pPlayer, menuProvider);
            }
        }

        return InteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SoulMirrorBE(pPos, pState);
    }
}
