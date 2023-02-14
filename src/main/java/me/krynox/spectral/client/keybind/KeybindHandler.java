package me.krynox.spectral.client.keybind;

import me.krynox.spectral.content.Registration;
import me.krynox.spectral.networking.SpectralKeybindAction;
import me.krynox.spectral.networking.SpectralKeybindMessage;
import me.krynox.spectral.networking.SpectralPacketHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeybindHandler {

	@SubscribeEvent
	public static void onMouseButton(InputEvent.MouseButton event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null || mc.screen != null) return;
		ISpellCaster cap = mc.player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();

		if (cap.isSpellcastingMode()) {
			if (event.getAction() == InputConstants.PRESS) {
				if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_1) {
					handleSpectralKeybind(mc.player, SpectralKeybindAction.CAST_0, true);
					SpectralPacketHandler.INSTANCE.sendToServer(new SpectralKeybindMessage(SpectralKeybindAction.CAST_0));
					event.setCanceled(true);
				} else if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_2) {
					handleSpectralKeybind(mc.player, SpectralKeybindAction.CAST_1, true);
					SpectralPacketHandler.INSTANCE.sendToServer(new SpectralKeybindMessage(SpectralKeybindAction.CAST_1));
					event.setCanceled(true);
				}
			}

			if (event.getAction() == InputConstants.RELEASE) {

			}
		}
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) { // Only call code once as the tick event is called twice every tick
			while (Keybinds.SPELLCASTING_MODE_MAPPING.get().consumeClick()) {
				handleSpectralKeybind(Minecraft.getInstance().player, SpectralKeybindAction.TOGGLE_MAGIC_MODE, true);
				SpectralPacketHandler.INSTANCE.sendToServer(new SpectralKeybindMessage(SpectralKeybindAction.TOGGLE_MAGIC_MODE));
			}
		}
	}

	public static void handleSpectralKeybind(Player player, SpectralKeybindAction action, boolean clientside) {
		if (player != null) {
			ISpellCaster cap = player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();

			if (action == SpectralKeybindAction.TOGGLE_MAGIC_MODE) {
				cap.toggleSpellcastingMode();
				cap.setSpell(0, Registration.TEST_SPELL.get());
			} else {
				if(player instanceof ServerPlayer && !clientside) {
					cap.castSpellServer(action.getId() - 1, (ServerPlayer) player, (ServerLevel) player.level, 0);
				} else if(player instanceof LocalPlayer && clientside) {
					cap.castSpellClient(action.getId() - 1, (LocalPlayer) player, (ClientLevel) player.level, 0);
				}
			}
		}
	}

}
