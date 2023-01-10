package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.entity.client.LeyRiftRenderer;
import me.krynox.spectral.entity.client.SpiritRenderer;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Spectral.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(Registration.LEY_RIFT_ENTITY.get(), LeyRiftRenderer::new);

        EntityRenderers.register(Registration.FIRE_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.FIRE));

        EntityRenderers.register(Registration.LIGHTNING_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.LIGHTNING));

        EntityRenderers.register(Registration.WIND_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.WIND));

        EntityRenderers.register(Registration.EARTH_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.EARTH));

        EntityRenderers.register(Registration.WATER_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.WATER));

        EntityRenderers.register(Registration.ICE_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.ICE));

        EntityRenderers.register(Registration.LIGHT_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.LIGHT));

        EntityRenderers.register(Registration.DARK_SPIRIT_ENTITY.get(), (x) ->
                new SpiritRenderer(x, MagicType.DARK));
    }
}
