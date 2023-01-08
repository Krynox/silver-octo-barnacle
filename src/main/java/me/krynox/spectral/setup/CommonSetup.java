package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent e) {

    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        e.put(Registration.FIRE_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.LIGHTNING_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.WIND_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.EARTH_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.WATER_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.ICE_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.LIGHT_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
        e.put(Registration.DARK_SPIRIT_ENTITY.get(), Mob.createMobAttributes().build());
    }
}
