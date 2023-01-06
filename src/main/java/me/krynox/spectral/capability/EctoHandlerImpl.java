package me.krynox.spectral.capability;

import me.krynox.spectral.Spectral;

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
    public int get(EctoType type) {
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
    public IEctoHandler set(EctoType type, int x) {
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
        return this;
    }
}
