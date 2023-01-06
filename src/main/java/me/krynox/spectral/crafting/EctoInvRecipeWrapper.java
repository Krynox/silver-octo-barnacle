package me.krynox.spectral.crafting;

import me.krynox.spectral.capability.IEctoHandler;
import me.krynox.spectral.util.InvUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Map;


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
     * For all (x, n) pairs in the input map, return whether there are at least n many x's in the inventory.
     * @param m A multiset represented as a map from member elements to their multiplicities.
     */
    public boolean hasAll(Map<Item, Integer> m) {
        Map<Item, Integer> invCounts = InvUtils.countItemsInInv(this.inv);

        for (Item i : m.keySet()) {
            if(invCounts.getOrDefault(i, 0) < m.get(i)) return false;
        }

        return true;
    }

}
