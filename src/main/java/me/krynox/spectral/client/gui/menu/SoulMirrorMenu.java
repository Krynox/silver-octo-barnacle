package me.krynox.spectral.client.gui.menu;

import me.krynox.spectral.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class SoulMirrorMenu extends AbstractContainerMenu {
    private final Player player;
    private final IItemHandler playerInv;

    //the pos of the clicked soul mirror. if null then assume it was spawned by an item rather than the mirror
    @Nullable
    private final BlockPos pos;

    public SoulMirrorMenu(int pContainerId, Inventory inv) {
        this(pContainerId, inv, inv.player, null);
    }

    public SoulMirrorMenu(int pContainerId, Inventory inv, Player player, BlockPos pos) {
        super(Registration.SOUL_MIRROR_MENU.get(), pContainerId);

        this.player = player;
        this.playerInv = new InvWrapper(inv);
        this.pos = pos;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {

        return this.pos == null
                || stillValid(ContainerLevelAccess.create(pPlayer.level, this.pos), pPlayer, Registration.SOUL_MIRROR_BLOCK.get());
    }
}
