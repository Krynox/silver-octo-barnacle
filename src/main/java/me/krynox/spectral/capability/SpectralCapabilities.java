package me.krynox.spectral.capability;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.ectohandler.IEctoHandler;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import me.krynox.spectral.capability.spellcaster.SpellCasterProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpectralCapabilities {
    public static final Capability<IEctoHandler> ECTO_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<ISpellCaster> SPELL_CASTER = CapabilityManager.get(new CapabilityToken<>() {});

    public static final ResourceLocation SPELL_CASTER_CAP_KEY = Spectral.resLoc("spellcaster_cap");

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            event.addCapability(SPELL_CASTER_CAP_KEY, new SpellCasterProvider());
            event.addListener(() -> event.getObject().getCapability(SPELL_CASTER).invalidate());
        }
    }

}
