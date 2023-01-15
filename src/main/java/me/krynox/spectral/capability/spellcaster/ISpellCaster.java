package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Optional;

@AutoRegisterCapability
public interface ISpellCaster {

    /**
     * The spell equipped in the specified slot.
     */
    Optional<Spell> getSpell(int slot);

    /**
     * Returns true if there is a spell equipped in the given slot.
     *
     */
    boolean isSpellEquipped(int slot);

    /**
     * Equip the given spell in the given slot.
     * Must `return this`, to allow for chaining.
     */
    ISpellCaster setSpell(int slot, Spell spell);

    /**
     * Cast the spell that is equipped in the slot.
     */
    void castSpell(int slot, Player player, Level level);

    /**
     * Whether the player is currently in spellcasting mode.
     * In spellcasting mode, the hotbar and inventory are inaccessible and items cannot be used.
     */
    boolean isSpellcastingMode();

    /**
     * Enter or leave spellcasting mode (or do nothing).
     */
    void setSpellcastingMode(boolean spellCastingMode);

    /**
     * Toggles spellcasting mode on or off.
     */
    void toggleSpellcastingMode();

    // Should slots/spells be tiered?
    // Should you have to attune slots to a particular type?

    int getFocus();
    
    int getMaxFocus();
    
    void setFocus(int focus);

    void addFocus(int i);
        
    CompoundTag serialize();
    void deserialize(CompoundTag tag);
}
