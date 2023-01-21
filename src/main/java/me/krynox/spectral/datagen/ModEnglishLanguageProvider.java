package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.client.keybind.Keybinds;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.MagicType;
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
        add("spectral.creative_tab.creativetab", "Spectral");
        add(Keybinds.SPECTRAL_KEYBIND_CATEGORY, "Spectral");
        add("death.attack.spectral.spectral_forge", "%s was shredded by the fabric of space-time.");

        for(MagicType t : MagicType.values()) {
            add(Registration.SPIRIT_SPAWN_EGGS(t).get(), "Spawn " + t.getNameCapitalised() + " Spirit");
            add(Registration.SPIRIT_ENTITIES(t).get(), t.getNameCapitalised() + " Spirit");
        }

        add(Registration.SOUL_MIRROR_BLOCK.get(), "Soul Mirror");
        add(Registration.SPECTRAL_FORGE_BLOCK.get(), "Heart of the Forge");
        add(Registration.SPIRIT_CAGE_BLOCK.get(), "Spirit Cage");

        add(Registration.SPECTRAL_MONOCLE_ITEM.get(), "Spectral Monocle");
        add(Registration.SPIRIT_CRYSTAL_ITEM.get(), "Spirit Crystal");

        add(Registration.ECTO_ITEMS(MagicType.FIRE).get(), "Molten Ectoplasm");
        addSplit(Registration.ECTO_ITEMS(MagicType.LIGHTNING).get(), "Energized Ectoplasm", "Energised Ectoplasm");
        add(Registration.ECTO_ITEMS(MagicType.WIND).get(), "Squalling Ectoplasm");
        add(Registration.ECTO_ITEMS(MagicType.EARTH).get(), "Adamantine Ectoplasm");
        add(Registration.ECTO_ITEMS(MagicType.WATER).get(), "Drowning Ectoplasm");
        add(Registration.ECTO_ITEMS(MagicType.ICE).get(), "Glacial Ectoplasm");
        add(Registration.ECTO_ITEMS(MagicType.DARK).get(), "Twilit Ectoplasm");
        add(Registration.ECTO_ITEMS(MagicType.LIGHT).get(), "Refulgent Ectoplasm");
    }
}
