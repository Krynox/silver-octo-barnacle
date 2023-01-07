package me.krynox.spectral.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvUtils {

    /**
     * @param inv The inventory, as an IItemHandler
     * @return A map which associates every Item in the inventory with the total number of times it appears there.
     */
    public static Map<Item, Integer> countItemsInInv(IItemHandler inv) {
        return countList(invToList(inv).stream().map(ItemStack::getItem).toList());
    }

    /**
     * Convert an inventory (as an IItemHandler) to a list of item stacks.
     */
    public static List<ItemStack> invToList(IItemHandler inv) {
        List<ItemStack> l = new ArrayList<>();

        for(int i = 0; i < inv.getSlots(); i++) {
            l.add(inv.getStackInSlot(i));
        }

        return l;
    }

    /**
     *
     * @param list A list of objects.
     * @return A map which associates every unique item in the list with the total
     * number of times it appears there (according to Obect#equals via Map#containsKey)
     * @param <K> The type parameter of the input list
     */
    public static <K> Map<K, Integer> countList(List<K> list) {
        Map<K, Integer> m = new HashMap<>();

        for(K k : list) {
            if(m.containsKey(k)) {
                m.replace(k, m.get(k) + 1);
            } else {
                m.put(k, 1);
            }
        }

        return m;
    }

}
