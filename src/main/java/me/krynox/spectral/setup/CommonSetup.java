package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.content.Registration;
import me.krynox.spectral.entity.SpiritEntity;
import me.krynox.spectral.magic.MagicType;
import me.krynox.spectral.networking.SpectralPacketHandler;
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
        SpectralPacketHandler.init(); // register network packets
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        for(MagicType t : MagicType.values()) {
            e.put(Registration.SPIRIT_ENTITIES(t).get(), Mob.createMobAttributes().build());
        }
    }

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(Registration.SPIRIT_ENTITIES(MagicType.WIND).get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpiritEntity.spawnPredicate(), SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
