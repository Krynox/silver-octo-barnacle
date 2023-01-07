package me.krynox.spectral.spell;

import me.krynox.spectral.setup.Registration;
import net.minecraft.Util;
import org.jetbrains.annotations.Nullable;

public class Spell {
    @Nullable
    private String descriptionId = null;

    public String getDescriptionId() {
        if(this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("spell", Registration.SPELLS_REEGISTRY.get().getKey(this));
        }
        return this.descriptionId;
    }
}
