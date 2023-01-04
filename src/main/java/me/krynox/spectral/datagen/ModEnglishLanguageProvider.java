package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnglishLanguageProvider extends LanguageProvider {

    private final boolean AMERICAN;

    public ModEnglishLanguageProvider(PackOutput output, String locale) {
        super(output, Spectral.MODID, locale);

        AMERICAN = locale.equals("en_us");
    }

    private void addSplit(Item key, String yankee, String queens) {
        if(AMERICAN) {
            add(key, yankee);
        } else {
            add(key, queens);
        }
    }

    @Override
    protected void addTranslations() {
        add(Spectral.MODID + ".creative_tab.creativetab", "Spectral");

        add(Registration.LEY_CONDUIT_BLOCK.get(), "Ley Conduit");
        add(Registration.SOUL_MIRROR_BLOCK.get(), "Soul Mirror");
        add(Registration.SPECTRAL_FORGE_BLOCK.get(), "Spectral Forge");
        add(Registration.SPIRIT_LOCUS_BLOCK.get(), "Spirit Locus");

        add(Registration.SPECTRAL_MONOCLE_ITEM.get(), "Spectral Monocle");
        add(Registration.SPIRIT_CRYSTAL_ITEM.get(), "Spirit Crystal");

        add(Registration.FIRE_ECTO_ITEM.get(), "Molten Ectoplasm");
        addSplit(Registration.LIGHTNING_ECTO_ITEM.get(), "Energized Ectoplasm", "Energised Ectoplasm");
        add(Registration.WIND_ECTO_ITEM.get(), "Squalling Ectoplasm");
        add(Registration.EARTH_ECTO_ITEM.get(), "Adamantine Ectoplasm");
        add(Registration.WATER_ECTO_ITEM.get(), "Drowning Ectoplasm");
        add(Registration.ICE_ECTO_ITEM.get(), "Glacial Ectoplasm");
        add(Registration.DARK_ECTO_ITEM.get(), "Twilit Ectoplasm");
        add(Registration.LIGHT_ECTO_ITEM.get(), "Refulgent Ectoplasm");

    }
}
