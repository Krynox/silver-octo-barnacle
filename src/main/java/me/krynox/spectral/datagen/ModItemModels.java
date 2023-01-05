package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Spectral.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(Registration.SPECTRAL_MONOCLE_ITEM.get());
        basicItem(Registration.SPIRIT_CRYSTAL_ITEM.get());

        basicItem(Registration.FIRE_ECTO_ITEM.get());
        basicItem(Registration.LIGHTNING_ECTO_ITEM.get());
        basicItem(Registration.WIND_ECTO_ITEM.get());
        basicItem(Registration.WATER_ECTO_ITEM.get());
        basicItem(Registration.EARTH_ECTO_ITEM.get());
        basicItem(Registration.ICE_ECTO_ITEM.get());
        basicItem(Registration.LIGHT_ECTO_ITEM.get());
        basicItem(Registration.DARK_ECTO_ITEM.get());
    }
}
