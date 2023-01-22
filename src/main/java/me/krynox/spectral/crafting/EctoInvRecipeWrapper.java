package me.krynox.spectral.crafting;

import me.krynox.spectral.capability.ectohandler.IEctoHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Optional;


/**
 * A wrapper around an IEctoHandler and an IItemHandler to use
 */
public class EctoInvRecipeWrapper extends RecipeWrapper {
    protected final IEctoHandler ectoHandler;

    public EctoInvRecipeWrapper(IItemHandlerModifiable inv, IEctoHandler ectoHandler) {
        super(inv);
        this.ectoHandler = ectoHandler;
    }

    public IEctoHandler getEctoHandler() {
        return ectoHandler;
    }

    /**
     * Try to insert the itemstack into the inventory (but NOT slot 0).
     * Return the remainder (so ItemStack.EMPTY for complete success)
     */
    public ItemStack insertItem(ItemStack item) {
        ItemStack remainder = item;
        for(int i = 1; i < inv.getSlots(); i++) {
            remainder = inv.insertItem(i, remainder, false);
            if(remainder == ItemStack.EMPTY) break;
        }
        return remainder;
    }

}
