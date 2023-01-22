package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.client.gui.FocusBarOverlay;
import me.krynox.spectral.client.gui.SpellHotbarOverlay;
import me.krynox.spectral.client.rendering.entity.LeyRiftRenderer;
import me.krynox.spectral.client.rendering.entity.SpiritRenderer;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Spectral.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(Registration.LEY_RIFT_ENTITY.get(), LeyRiftRenderer::new);

        for(MagicType t : MagicType.values()) {
            EntityRenderers.register(Registration.SPIRIT_ENTITIES(t).get(), (x) ->
                    new SpiritRenderer(x, t));
        }
    }
    
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
    	event.registerAboveAll("spell_hotbar", new SpellHotbarOverlay());
    	event.registerAboveAll("focus_bar", new FocusBarOverlay());

    }
    

}
