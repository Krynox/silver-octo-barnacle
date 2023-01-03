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
                .icon(() -> new ItemStack(Registration.SPIRIT_CRYSTAL_ITEM.get()))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "creativetab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(Registration.LEY_CONDUIT_ITEM.get());
                    output.accept(Registration.SOUL_MIRROR_ITEM.get());
                    output.accept(Registration.SPECTRAL_FORGE_ITEM.get());
                    output.accept(Registration.SPIRIT_LOCUS_ITEM.get());

                    output.accept(Registration.SPECTRAL_MONOCLE_ITEM.get());
                    output.accept(Registration.SPIRIT_CRYSTAL_ITEM.get());


                }));
    }

}
