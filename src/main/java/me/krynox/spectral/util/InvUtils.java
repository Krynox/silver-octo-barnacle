package me.krynox.spectral.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvUtils {

    public static Map<Item, Integer> countItemsInInv(IItemHandler inv) {
        return countList(invToList(inv).stream().map(ItemStack::getItem).toList());
    }

    public static List<ItemStack> invToList(IItemHandler inv) {
        List<ItemStack> l = new ArrayList<>();

        for(int i = 0; i < inv.getSlots(); i++) {
            l.add(inv.getStackInSlot(i));
        }

        return l;
    }

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
