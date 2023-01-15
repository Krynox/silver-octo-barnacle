package me.krynox.spectral.spell.client;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.client.keybind.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SpellcastingModeGuiEventHandlers {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        if(Minecraft.getInstance().player
        		.getCapability(SpectralCapabilities.SPELL_CASTER)
        		.resolve().get().isSpellcastingMode() 
        	) {
        	
        	String overlay = event.getOverlay().id().getPath();
        	if (overlay.equals("hotbar") || overlay.equals("experience_bar")) {
        		event.setCanceled(true);
        	}
        }
    }
}
