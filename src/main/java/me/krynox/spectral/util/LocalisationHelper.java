package me.krynox.spectral.util;

import me.krynox.spectral.Spectral;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 A class for providing utility methods to help with creating unlocalised names (known in the current iteration of
 forge/mc as a "Component").
  */
public class LocalisationHelper {

    public static MutableComponent newUnlocName(LocalisedTextCategory cat, String name) {
        return Component.translatable(Spectral.MODID + "." + cat.toString().toLowerCase() + "." + name);
    }
}
