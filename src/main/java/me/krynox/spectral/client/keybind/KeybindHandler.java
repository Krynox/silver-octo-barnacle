package me.krynox.spectral.client.keybind;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeybindHandler {
	
	@SubscribeEvent
	public static void onMouseButton(InputEvent.MouseButton event) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.player == null || mc.screen != null) return;
		ISpellCaster cap = mc.player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();
		
		if(cap.isSpellcastingMode()) {
			if(event.getAction() == InputConstants.PRESS) {
				if(event.getButton() == GLFW.GLFW_MOUSE_BUTTON_1) {
					cap.castSpell(0, mc.player, mc.level);
					event.setCanceled(true);
				} else if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_2) {
					cap.castSpell(1, mc.player, mc.level);
					event.setCanceled(true);
				}
			}
		
			if(event.getAction() == InputConstants.RELEASE) {
			
			}
		}
	}
	
	
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) { // Only call code once as the tick event is called twice every tick
            while (Keybinds.SPELLCASTING_MODE_MAPPING.get().consumeClick()) {
                ISpellCaster cap = Minecraft.getInstance().player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();
                cap.toggleSpellcastingMode();
            }
        }
    }
    
}
