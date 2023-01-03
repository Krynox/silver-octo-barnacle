package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnglishLanguageProvider extends LanguageProvider {

    private final boolean AMERICAN;

    public ModEnglishLanguageProvider(PackOutput output, String locale) {
        super(output, Spectral.MODID, locale);

        AMERICAN = locale.equals("en_us");
    }

    @Override
    protected void addTranslations() {
        add("spectral.creative_tab.creativetab", "Spectral");

        add(Registration.LEY_CONDUIT_BLOCK.get(), "Ley Conduit");
        add(Registration.SOUL_MIRROR_BLOCK.get(), "Soul Mirror");
        add(Registration.SPECTRAL_FORGE_BLOCK.get(), "Spectral Forge");
        add(Registration.SPIRIT_LOCUS_BLOCK.get(), "Spirit Locus");

        add(Registration.SPECTRAL_MONOCLE_ITEM.get(), "Spectral Monocle");
        add(Registration.SPIRIT_CRYSTAL_ITEM.get(), "Spirit Crystal");

        if(AMERICAN) {
            // American spellings here
        } else {
            // British/Aussie/Canuck spellings here
        }
    }
}
