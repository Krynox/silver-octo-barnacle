package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.client.gui.overlay.FocusBarOverlay;
import me.krynox.spectral.client.gui.overlay.SpellHotbarOverlay;
import me.krynox.spectral.client.gui.screen.SoulMirrorScreen;
import me.krynox.spectral.client.rendering.block.SpectralForgeRenderer;
import me.krynox.spectral.client.rendering.block.SpiritCageRenderer;
import me.krynox.spectral.client.rendering.entity.LeyRiftRenderer;
import me.krynox.spectral.client.rendering.entity.SpiritRenderer;
import me.krynox.spectral.content.Registration;
import me.krynox.spectral.magic.MagicType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Spectral.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.SOUL_MIRROR_MENU.get(), SoulMirrorScreen::new);

            EntityRenderers.register(Registration.LEY_RIFT_ENTITY.get(), LeyRiftRenderer::new);

            for (MagicType t : MagicType.values()) {
                EntityRenderers.register(Registration.SPIRIT_ENTITIES(t).get(), (x) ->
                        new SpiritRenderer(x, t));
            }
        });
    }
    
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
    	event.registerAboveAll("spell_hotbar", new SpellHotbarOverlay());
    	event.registerAboveAll("focus_bar", new FocusBarOverlay());

    }

    @SubscribeEvent
    public static void registerBERs(EntityRenderersEvent.RegisterRenderers e) {
        e.registerBlockEntityRenderer(Registration.SPECTRAL_FORGE_BE.get(), (ctx) -> new SpectralForgeRenderer());
        e.registerBlockEntityRenderer(Registration.SPIRIT_CAGE_BE.get(), (ctx) -> new SpiritCageRenderer());
    }
    

}
