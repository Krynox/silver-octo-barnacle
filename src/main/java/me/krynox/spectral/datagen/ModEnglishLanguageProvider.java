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

        if(AMERICAN) {
            // American spellings here
        } else {
            // British/Aussie/Canuck spellings here
        }
    }
}
