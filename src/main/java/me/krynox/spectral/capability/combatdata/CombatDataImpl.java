package me.krynox.spectral.capability.combatdata;

import me.krynox.spectral.magic.BuffStack;

import java.util.ArrayList;
import java.util.List;

public class CombatDataImpl implements ICombatData {
    private final List<BuffStack> buffs = new ArrayList<>();

    public CombatDataImpl() {
    }

    @Override
    public List<BuffStack> getBuffs() {
        return buffs;
    }

}
