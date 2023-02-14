package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.magic.AbstractSpell;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.items.IItemHandler;

import java.util.Optional;

@AutoRegisterCapability
public interface ISpellCaster {

    //// Spells

    /**
     * The total number of spell slots.
     */
	int getSpellSlots();
	
    /**
     * The spell equipped in the specified slot.
     */
    Optional<AbstractSpell> getSpell(int slot);

    /**
     * Returns true if there is a spell equipped in the given slot.
     *
     */
    boolean isSpellEquipped(int slot);

    /**
     * Equip the given spell in the given slot.
     * Must `return this`, to allow for chaining.
     */
    ISpellCaster setSpell(int slot, AbstractSpell spell);

    /**
     * Cast the spell that is equipped in the slot.
     */
    void castSpellClient(int slot, LocalPlayer player, ClientLevel level, float partialTicks);
    void castSpellServer(int slot, ServerPlayer player, ServerLevel level, float partialTicks);

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


    /**
     * Get the soul mirror inventory for this player.
     */
    IItemHandler getSoulMirrorInv();

    /**
     * Sync the current state of the soul mirror inventory to the spells list.
     * Invalid items in slots are ignored and treated as empty slots.
     * With that said, the slots shouldn't be accepting invalid items in the first place.
     */
    void syncSoulMirrorState();

    //// Focus

    int getFocus();
    
    int getMaxFocus();
    
    void setFocus(int focus);

    void addFocus(int i);

    //// NBT

    CompoundTag serialize();
    void deserialize(CompoundTag tag);
}
