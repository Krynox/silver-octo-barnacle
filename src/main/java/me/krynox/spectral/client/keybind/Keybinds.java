package me.krynox.spectral.client.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import me.krynox.spectral.Spectral;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.client.settings.KeyConflictContext.IN_GAME;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Keybinds {
    public static final String SPECTRAL_KEYBIND_CATEGORY = "key.categories.spectral";

    public static Lazy<KeyMapping> SPELLCASTING_MODE_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spellcasting_mode", InputConstants.KEY_C, InputConstants.Type.KEYSYM, IN_GAME));

    /*
    public static Lazy<KeyMapping> SPELL_SLOT_1_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spell_slot_1", InputConstants.MOUSE_BUTTON_LEFT, InputConstants.Type.MOUSE, IN_GAME));

    public static Lazy<KeyMapping> SPELL_SLOT_2_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spell_slot_2", InputConstants.MOUSE_BUTTON_RIGHT, InputConstants.Type.MOUSE, IN_GAME));

    public static Lazy<KeyMapping> SPELL_SLOT_3_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spell_slot_3", InputConstants.KEY_1, InputConstants.Type.KEYSYM, IN_GAME));

    public static Lazy<KeyMapping> SPELL_SLOT_4_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spell_slot_4", InputConstants.KEY_2, InputConstants.Type.KEYSYM, IN_GAME));

    public static Lazy<KeyMapping> SPELL_SLOT_5_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spell_slot_5", InputConstants.KEY_3, InputConstants.Type.KEYSYM, IN_GAME));

    public static Lazy<KeyMapping> SPELL_SLOT_6_MAPPING = Lazy.of(() ->
            spectralKeyMapping("spell_slot_6", InputConstants.KEY_4, InputConstants.Type.KEYSYM, IN_GAME));
	*/
    
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SPELLCASTING_MODE_MAPPING.get());
        
        /*
        event.register(SPELL_SLOT_1_MAPPING.get());
        event.register(SPELL_SLOT_2_MAPPING.get());
        event.register(SPELL_SLOT_3_MAPPING.get());
        event.register(SPELL_SLOT_4_MAPPING.get());
        event.register(SPELL_SLOT_5_MAPPING.get());
        event.register(SPELL_SLOT_6_MAPPING.get());
        */
    }

    private static KeyMapping spectralKeyMapping(String name, int defaultKey, InputConstants.Type inputType, IKeyConflictContext conflictContext) {
        return new KeyMapping("key.spectral." + name, conflictContext, inputType, defaultKey, SPECTRAL_KEYBIND_CATEGORY);
    }
}
