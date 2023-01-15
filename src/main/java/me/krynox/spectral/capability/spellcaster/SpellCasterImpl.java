package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.Spell;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class SpellCasterImpl implements ISpellCaster {
    public SpellCasterImpl() {

    }

    public static final int SPELL_SLOTS = 6;
    public static final int MAX_FOCUS = 7;
    
    private Spell[] spells = new Spell[SPELL_SLOTS];
    private boolean spellcastingMode = false;
    private int focus = 0;
    
    @Override
    public int getSpellSlots() {
    	return SPELL_SLOTS;
    }

    @Override
    public Optional<Spell> getSpell(int slot) {
        if(slot < 0 || slot > SPELL_SLOTS) {
            Spectral.LOGGER.error("Tried to get spell from out-of-bounds slot " + slot + ".");
            return Optional.empty();
        }
        return Optional.of(spells[slot]);
    }

    @Override
    public boolean isSpellEquipped(int slot) {
        return slot > 0 && slot < SPELL_SLOTS && spells[slot] != null;
    }


    @Override
    public ISpellCaster setSpell(int slot, Spell spell) {
        if(slot < 0 || slot > SPELL_SLOTS) {
            Spectral.LOGGER.error("Tried to set spell in out-of-bounds slot " + slot + ".");
        }

        spells[slot] = spell;
        return this;
    }

    @Override
    public void castSpell(int slot, Player player, Level level) {
        if(slot < 0 || slot > SPELL_SLOTS) {
            Spectral.LOGGER.error("Tried to cast spell in out-of-bounds slot " + slot + ".");
        }

        Spell s = spells[slot];
        if(s == null) {
            Spectral.LOGGER.debug("Tried to cast non-equipped spell.");
            return;
        }

        if(player instanceof ServerPlayer && level instanceof ServerLevel) {
            Spectral.LOGGER.debug("Server casting " + slot);
            s.castServer((ServerPlayer) player, (ServerLevel) level);
        } else if(player instanceof LocalPlayer && level instanceof ClientLevel) {
            Spectral.LOGGER.debug("Client casting " + slot);
            s.castClient((LocalPlayer) player, (ClientLevel) level);
        }
    }

    @Override
    public boolean isSpellcastingMode() {
        return spellcastingMode;
    }

    @Override
    public void setSpellcastingMode(boolean pSpellcastingMode) {
        this.spellcastingMode = pSpellcastingMode;
    }

    @Override
    public void toggleSpellcastingMode() {
        spellcastingMode = !spellcastingMode;
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag output = new CompoundTag();
        ListTag spellsTag = new ListTag();

        for(Spell spell : spells) {
            if(spell != null) spellsTag.add(StringTag.valueOf(Registration.SPELLS_REEGISTRY.get().getKey(spell).getPath()));
        }

        output.putBoolean("spellcastingMode", spellcastingMode);
        
        if(!spellsTag.isEmpty()) output.put("spells", spellsTag);

        return output;
    }

    @Override
    public void deserialize(CompoundTag tag) {
        this.spellcastingMode = tag
                .getBoolean("spellcastingMode");

        List<Spell> foo = tag
                .getList("spells", 8) //8 is the magic number for String, so this is a list of string tags
                .stream()
                .map((stringTag) -> Registration.SPELLS_REEGISTRY.get().getValue(new ResourceLocation(stringTag.getAsString())))
                .toList();


        this.spells = new Spell[foo.size()];
        foo.toArray(spells); // reminder: old java api, it mutates the array passed in
    }

	@Override
	public int getFocus() {
		return focus;
	}

	@Override
	public int getMaxFocus() {
		return MAX_FOCUS;
	}

	@Override
	public void setFocus(int focus) {
		if(focus < 0) {
			this.focus = 0;
		} else if(focus > MAX_FOCUS) {
			this.focus = MAX_FOCUS;
		} else {
			this.focus = focus;
		}
	}

	@Override
	public void addFocus(int i) {
		setFocus(this.focus + i);
	}
}
