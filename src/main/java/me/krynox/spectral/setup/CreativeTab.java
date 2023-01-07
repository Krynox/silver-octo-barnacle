package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.localisation.LocalisationHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.krynox.spectral.localisation.LocalisedTextCategory.CREATIVE_TAB;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {
    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register e) {

        e.registerCreativeModeTab(Spectral.resLoc("creativetab"), builder -> builder
                .icon(() -> new ItemStack(Registration.SPIRIT_CRYSTAL_ITEM.get()))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "creativetab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(Registration.SPIRIT_SPAWN_EGG_ITEM.get());

                    output.accept(Registration.SOUL_MIRROR_ITEM.get());
                    output.accept(Registration.SPECTRAL_FORGE_ITEM.get());

                    output.accept(Registration.SPECTRAL_MONOCLE_ITEM.get());
                    output.accept(Registration.SPIRIT_CRYSTAL_ITEM.get());

                    output.accept(Registration.FIRE_ECTO_ITEM.get());
                    output.accept(Registration.LIGHTNING_ECTO_ITEM.get());
                    output.accept(Registration.WIND_ECTO_ITEM.get());
                    output.accept(Registration.WATER_ECTO_ITEM.get());
                    output.accept(Registration.EARTH_ECTO_ITEM.get());
                    output.accept(Registration.ICE_ECTO_ITEM.get());
                    output.accept(Registration.LIGHT_ECTO_ITEM.get());
                    output.accept(Registration.DARK_ECTO_ITEM.get());

                }));
    }
}
