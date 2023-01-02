package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.localisation.LocalisationHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.krynox.spectral.localisation.LocalisedTextCategory.CREATIVE_TAB;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {
    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register e) {

        e.registerCreativeModeTab(Spectral.resLoc("creativetab"), builder -> builder
                .icon(() -> new ItemStack(Items.ACACIA_BOAT))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "creativetab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(Items.ACACIA_BOAT);


                }));
    }

}
