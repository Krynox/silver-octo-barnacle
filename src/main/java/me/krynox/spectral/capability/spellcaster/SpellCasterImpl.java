package me.krynox.spectral.capability.spellcaster;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.magic.AbstractSpell;
import me.krynox.spectral.setup.Registration;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SpellCasterImpl implements ISpellCaster {
    public SpellCasterImpl() {
        this.soulMirrorInv = new ItemStackHandler(SPELL_SLOTS);
    }

    public static final int SPELL_SLOTS = 6;
    public static final int MAX_FOCUS = 7;

    // The inventory the player interacts with via the soul mirror
    private final IItemHandler soulMirrorInv;

    private AbstractSpell[] spells = new AbstractSpell[SPELL_SLOTS];
    private boolean spellcastingMode = false;
    private int focus = 0;
    
    @Override
    public int getSpellSlots() {
    	return SPELL_SLOTS;
    }

    @Override
    public Optional<AbstractSpell> getSpell(int slot) {
        if(slot < 0 || slot > SPELL_SLOTS) {
            Spectral.LOGGER.error("Tried to get spell from out-of-bounds slot " + slot + ".");
            return Optional.empty();
        }
        return Optional.ofNullable(spells[slot]);
    }

    @Override
    public boolean isSpellEquipped(int slot) {
        return slot > 0 && slot < SPELL_SLOTS && spells[slot] != null;
    }


    @Override
    public ISpellCaster setSpell(int slot, @Nullable AbstractSpell spell) {
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

        AbstractSpell s = spells[slot];
        if(s == null) return;

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
    public IItemHandler getSoulMirrorInv() {
        return soulMirrorInv;
    }

    @Override
    public void syncSoulMirrorState() {
        for(int i = 0; i<SPELL_SLOTS; i++) {
            ItemStack item = soulMirrorInv.getStackInSlot(i);
            CompoundTag tag = item.getTag();
            if (tag != null) {
                ResourceLocation key = new ResourceLocation(tag.getCompound("spectral").getString("spell"));
                AbstractSpell spell = Registration.SPELLS_REGISTRY.get().getValue(key);
                setSpell(i, spell);
            }
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag output = new CompoundTag();
        ListTag spellsTag = new ListTag();

        for(AbstractSpell spell : spells) {
            if(spell != null) spellsTag.add(StringTag.valueOf(Registration.SPELLS_REGISTRY.get().getKey(spell).getPath()));
        }

        output.putBoolean("spellcastingMode", spellcastingMode);
        
        if(!spellsTag.isEmpty()) output.put("spells", spellsTag);

        return output;
    }

    @Override
    public void deserialize(CompoundTag tag) {
        this.spellcastingMode = tag
                .getBoolean("spellcastingMode");

        List<AbstractSpell> foo = tag
                .getList("spells", Tag.TAG_STRING)
                .stream()
                .map((stringTag) -> Registration.SPELLS_REGISTRY.get().getValue(new ResourceLocation(stringTag.getAsString())))
                .toList();


        this.spells = new AbstractSpell[foo.size()];
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
