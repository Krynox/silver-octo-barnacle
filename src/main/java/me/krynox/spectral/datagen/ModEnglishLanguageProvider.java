package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.Spell;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnglishLanguageProvider extends LanguageProvider {

    private final boolean AMERICAN;

    public ModEnglishLanguageProvider(PackOutput output, String locale) {
        super(output, Spectral.MODID, locale);

        AMERICAN = locale.equals("en_us");
    }

    public void addSplit(Item key, String american, String british) {
        addSplit(key.getDescriptionId(), american, british);
    }

    public void addSplit(String key, String american, String british) {
        if(AMERICAN) {
            add(key, american);
        } else {
            add(key, british);
        }
    }

    public void add(Spell key, String name) {
        add(key.getDescriptionId(), name);
    }


    @Override
    protected void addTranslations() {
        add(Spectral.MODID + ".creative_tab.creativetab", "Spectral");

        add(Registration.FIRE_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Fire Spirit");
        add(Registration.LIGHTNING_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Lightning Spirit");
        add(Registration.WIND_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Wind Spirit");
        add(Registration.EARTH_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Earth Spirit");
        add(Registration.WATER_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Water Spirit");
        add(Registration.ICE_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Ice Spirit");
        add(Registration.LIGHT_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Light Spirit");
        add(Registration.DARK_SPIRIT_SPAWN_EGG_ITEM.get(), "Spawn Dark Spirit");

        add(Registration.SOUL_MIRROR_BLOCK.get(), "Soul Mirror");
        add(Registration.SPECTRAL_FORGE_BLOCK.get(), "Spectral Forge");

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

        add(Registration.FIRE_SPIRIT_ENTITY.get(), "Fire Spirit");
        add(Registration.LIGHTNING_SPIRIT_ENTITY.get(), "Lightning Spirit");
        add(Registration.WIND_SPIRIT_ENTITY.get(), "Wind Spirit");
        add(Registration.EARTH_SPIRIT_ENTITY.get(), "Earth Spirit");
        add(Registration.WATER_SPIRIT_ENTITY.get(), "Water Spirit");
        add(Registration.ICE_SPIRIT_ENTITY.get(), "Ice Spirit");
        add(Registration.LIGHT_SPIRIT_ENTITY.get(), "Light Spirit");
        add(Registration.DARK_SPIRIT_ENTITY.get(), "Dark Spirit");
    }
}
