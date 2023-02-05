package me.krynox.spectral.inventory.menu;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.content.Registration;
import me.krynox.spectral.content.SpectralTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SoulMirrorMenu extends AbstractContainerMenu {
    private static final int SM_START_X = 44;
    private static final int SM_START_Y = 20;
    private static final int INV_START_X = 8;
    private static final int INV_START_Y = 51;
    private static final int HOTBAR_START_X = 8;
    private static final int HOTBAR_START_Y = 109;

    private static final int SLOT_WIDTH = 18;

    private final Player player;
    private final IItemHandler playerInv;
    private final IItemHandler soulMirrorInv;


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
        //happy to throw the exception if this fails, can't imagine trying to construct a menu when the cap isnt there
        this.soulMirrorInv = player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get().getSoulMirrorInv();
        this.pos = pos;

        // slots for the soul mirror inv
        for(int i = 0; i < soulMirrorInv.getSlots(); i++) {
            this.addSlot(new SlotItemHandler(soulMirrorInv, i, SM_START_X + (i * SLOT_WIDTH), SM_START_Y){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.is(SpectralTags.SPELL_CRYSTAL);
                }
            });
        }

        //hotbar slots
        for(int i = 0; i < 9 ; i++) {
            this.addSlot(new SlotItemHandler(playerInv, i, HOTBAR_START_X + (i * SLOT_WIDTH), HOTBAR_START_Y));

        }

        //slots for the rest of the player inv
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlot(new SlotItemHandler(playerInv, 9 + (i * 9) + j, INV_START_X + (j * SLOT_WIDTH), INV_START_Y + (i * SLOT_WIDTH)));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if(slot.hasItem()) {
            if(((SlotItemHandler) slot).getItemHandler() == playerInv) {
                Spectral.LOGGER.info("Shift-clicked item in player inventory.");

            } else if(((SlotItemHandler) slot).getItemHandler() == soulMirrorInv) {
                Spectral.LOGGER.info("Shift-clicked item in soul mirror inventory.");

            } else {
                Spectral.LOGGER.error("If you're seeing this message, shift-clicking in the Soul Mirror GUI is currently bugged.");
            }
        }


        return itemStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {

        return this.pos == null
                || stillValid(ContainerLevelAccess.create(pPlayer.level, this.pos), pPlayer, Registration.SOUL_MIRROR_BLOCK.get());
    }
}
