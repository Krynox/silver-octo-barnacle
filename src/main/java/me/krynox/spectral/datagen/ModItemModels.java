package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.content.Registration;
import me.krynox.spectral.magic.MagicType;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Spectral.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(MagicType t : MagicType.values()) {
            withExistingParent(Registration.SPIRIT_SPAWN_EGGS(t).getId().getPath(), mcLoc("item/template_spawn_egg"));
            basicItem(Registration.ECTO_ITEMS(t).get());

        }

        basicItem(Registration.SPECTRAL_MONOCLE_ITEM.get());
        basicItem(Registration.SPIRIT_CRYSTAL_ITEM.get());
    }
}
