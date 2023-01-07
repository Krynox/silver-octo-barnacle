package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ISpellCaster {

    /**
     * The number of spell slots the caster has access to.
     */
    int getSpells();

    /**
     * The spell equipped in the specified slot.
     */
    Spell getSpellInSlot(int slot);

    /**
     * Equip the given spell in the given slot.
     * Must `return this`, to allow for chaining.
     */
    ISpellCaster setSpellInSlot(int slot, Spell spell);

    // Mana?
    // The ecto-as-mana idea?
    // Should slots/spells be tiered?
    // Should you have to attune slots to a particular type?


    CompoundTag serialize();
    void deserialize(CompoundTag tag);
}
