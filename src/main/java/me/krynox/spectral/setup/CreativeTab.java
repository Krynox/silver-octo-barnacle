package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.spell.MagicType;
import me.krynox.spectral.util.LocalisationHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.krynox.spectral.util.LocalisedTextCategory.CREATIVE_TAB;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {
    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register e) {

        e.registerCreativeModeTab(Spectral.resLoc("creativetab"), builder -> builder
                .icon(() -> new ItemStack(Registration.SPIRIT_CRYSTAL_ITEM.get()))
                .title(LocalisationHelper.newUnlocName(CREATIVE_TAB, "creativetab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    for(MagicType t : MagicType.values()) {
                        output.accept(Registration.SPIRIT_SPAWN_EGGS(t).get());
                    }

                    for(MagicType t : MagicType.values()) {
                        output.accept(Registration.ECTO_ITEMS(t).get());
                    }

                    output.accept(Registration.SOUL_MIRROR_ITEM.get());
                    output.accept(Registration.SPECTRAL_FORGE_ITEM.get());
                    output.accept(Registration.SPIRIT_CAGE_BLOCK.get());

                    output.accept(Registration.SPECTRAL_MONOCLE_ITEM.get());
                    output.accept(Registration.SPIRIT_CRYSTAL_ITEM.get());
                }));
    }
}
