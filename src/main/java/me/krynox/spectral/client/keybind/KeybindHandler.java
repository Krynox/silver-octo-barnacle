package me.krynox.spectral.client.keybind;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeybindHandler {


    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            while (Keybinds.SPELLCASTING_MODE_MAPPING.get().consumeClick()) {
                ISpellCaster cap = Minecraft.getInstance().player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();
                cap.toggleSpellcastingMode();
            }

            Minecraft mc = Minecraft.getInstance();

            if(mc.player == null) return;

            ISpellCaster cap = mc.player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();

            while (Keybinds.SPELL_SLOT_1_MAPPING.get().consumeClick()) {
                if(cap.isSpellcastingMode()) cap.castSpell(0, mc.player, mc.level);
            }

            while (Keybinds.SPELL_SLOT_2_MAPPING.get().consumeClick()) {
                if(cap.isSpellcastingMode()) cap.castSpell(1, mc.player, mc.level);
            }

            while (Keybinds.SPELL_SLOT_3_MAPPING.get().consumeClick()) {
                if(cap.isSpellcastingMode()) cap.castSpell(2, mc.player, mc.level);
            }

            while (Keybinds.SPELL_SLOT_4_MAPPING.get().consumeClick()) {
                if(cap.isSpellcastingMode()) cap.castSpell(3, mc.player, mc.level);
            }

            while (Keybinds.SPELL_SLOT_5_MAPPING.get().consumeClick()) {
                if(cap.isSpellcastingMode()) cap.castSpell(4, mc.player, mc.level);
            }

            while (Keybinds.SPELL_SLOT_6_MAPPING.get().consumeClick()) {
                if(cap.isSpellcastingMode()) cap.castSpell(5, mc.player, mc.level);
            }
        }
    }
}
