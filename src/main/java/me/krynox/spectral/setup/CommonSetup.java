package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.entity.SpiritEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
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

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(Registration.WIND_SPIRIT_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpiritEntity.spawnPredicate(), SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
