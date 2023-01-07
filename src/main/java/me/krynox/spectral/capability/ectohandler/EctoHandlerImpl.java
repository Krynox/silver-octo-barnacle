package me.krynox.spectral.capability.ectohandler;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.nbt.CompoundTag;

public class EctoHandlerImpl implements IEctoHandler {
    int fire = 0;
    int lightning = 0;
    int wind = 0;
    int earth = 0;
    int water = 0;
    int ice = 0;
    int light = 0;
    int dark = 0;

    @Override
    public int get(MagicType type) {
        int result = 0;

        switch (type){
            case FIRE -> result = fire;
            case LIGHTNING -> result = lightning;
            case WIND -> result = wind;
            case EARTH -> result = earth;
            case WATER -> result = water;
            case ICE -> result = ice;
            case LIGHT -> result = light;
            case DARK -> result = dark;
            default -> Spectral.LOGGER.error("Incomplete pattern matching in EctoHandlerImpl#get");
        }

        return result;
    }

    @Override
    public IEctoHandler set(MagicType type, int x) {
        Spectral.LOGGER.info("Fire then: " + fire + ", adding " + x);
        switch (type) {
            case FIRE -> fire = x;
            case LIGHTNING -> lightning = x;
            case WIND -> wind = x;
            case EARTH -> earth = x;
            case WATER -> water = x;
            case ICE -> ice = x;
            case LIGHT -> light = x;
            case DARK -> dark = x;
        }
        Spectral.LOGGER.info("Fire now: " + fire);
        return this;
    }


    @Override
    public CompoundTag serialize() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("fire", fire);
        tag.putInt("lightning", lightning);
        tag.putInt("wind", wind);
        tag.putInt("earth", earth);
        tag.putInt("water", water);
        tag.putInt("ice", ice);
        tag.putInt("light", light);
        tag.putInt("dark", dark);
        return tag;
    }

    @Override
    public void deserialize(CompoundTag tag) {
        fire = tag.getInt("fire");
        lightning = tag.getInt("lightning");
        wind = tag.getInt("wind");
        earth = tag.getInt("earth");
        water = tag.getInt("water");
        ice = tag.getInt("ice");
        light = tag.getInt("light");
        dark = tag.getInt("dark");
    }
}
