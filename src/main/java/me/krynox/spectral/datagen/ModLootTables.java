package me.krynox.spectral.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ModLootTables extends LootTableProvider {
    //registry names of tables required to generate
    private static final Set<ResourceLocation> resLocs = Collections.emptySet();

    //sub-providers which generate the loot
    private static final List<SubProviderEntry> subPs = Collections.emptyList();

    public ModLootTables(PackOutput output) {
        super(output, resLocs, subPs);
    }
}
