package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class SpellCasterImpl implements ISpellCaster {

    private List<Spell> spells = new ArrayList<>(5);

    @Override
    public int getSpells() {
        return spells.size();
    }

    @Override
    public Spell getSpellInSlot(int slot) {
        return spells.get(slot);
    }

    @Override
    public ISpellCaster setSpellInSlot(int slot, Spell spell) {
        spells.set(slot, spell);
        return this;
    }

    @Override
    public CompoundTag serialize() {
        ListTag tag = new ListTag();
        for(Spell spell : spells) {
            tag.add(StringTag.valueOf(Registration.SPELLS_REEGISTRY.get().getKey(spell).getPath()));
        }
        return (CompoundTag) new CompoundTag().put("spells", tag);
    }

    @Override
    public void deserialize(CompoundTag tag) {
        this.spells = tag
                .getList("spells", 8) //8 is the magic number for String, so this is a list of string tags
                .stream()
                .map((stringTag) -> Registration.SPELLS_REEGISTRY.get().getValue(new ResourceLocation(stringTag.getAsString())))
                .toList();
    }
}
