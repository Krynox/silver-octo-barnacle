package me.krynox.spectral.magic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum MagicType {
    FIRE("fire", 0xee444, 0xff6666),
    LIGHTNING("lightning", 0xee444, 0xff6666),
    WIND("wind", 0xee444, 0xff6666),
    EARTH("earth", 0xee444, 0xff6666),
    WATER("water", 0xee444, 0xff6666),
    ICE("ice", 0xee444, 0xff6666),
    LIGHT("light", 0xee444, 0xff6666),
    DARK("dark", 0xee444, 0xff6666);


    private final int primaryColour;
    private final int secondaryColour;
    private final String name;

    MagicType(String name, int primaryColour, int secondaryColour) {
        this.name = name;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
    }

    public int getPrimaryColour() {
        return primaryColour;
    }

    public int getSecondaryColour() {
        return secondaryColour;
    }

    public String getName() {
        return name;
    }

    public String getNameCapitalised() {
        char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }

    public static <V> Map<MagicType, V> tabulate(Function<MagicType, V> f) {
        Map<MagicType, V> m = new HashMap<>();

        for(MagicType t : MagicType.values()) {
            m.put(t, f.apply(t));
        }

        return m;
    }
}
