package me.krynox.spectral.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import me.krynox.spectral.client.gui.FocusBarOverlay.PipType;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SpellHotbarOverlay implements IGuiOverlay {
	private static final int TEX_WIDTH = 24;
	private static final int TEX_HEIGHT = 24;
	private static final int U_WIDTH = 24;
	private static final int V_HEIGHT = 24;
	private static final ResourceLocation TEXTURE = Spectral.resLoc("textures/gui/spell_bar.png");


	@Override
	public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
    	RenderSystem.setShaderTexture(0, TEXTURE);

		LocalPlayer player = gui.getMinecraft().player;
		if(player != null) {
			player.getCapability(SpectralCapabilities.SPELL_CASTER)
					.resolve()
					.ifPresent((cap) -> {
						if (cap.isSpellcastingMode()) renderBar(poseStack, cap.getSpellSlots(), screenWidth, screenHeight);
					});
		}
	}
	
	private void renderBar(PoseStack poseStack, int slots, int screenWidth, int screenHeight) {
		int x = Mth.ceil(screenWidth / 2) - (U_WIDTH * slots / 2);
		int y = Mth.ceil(screenHeight * 90 / 100);
		
		for(int i = 0; i < slots; i++) {
			renderSlot(poseStack, x, y);
			x += U_WIDTH;
		}
	}
	
	private void renderSlot(PoseStack poseStack, int x, int y) {  
		int blitOffset = 0;
		int uOffset = 0;
		int vOffset = 0;

		GuiComponent.blit(poseStack, x, y, blitOffset, uOffset, vOffset, U_WIDTH, V_HEIGHT, TEX_WIDTH, TEX_HEIGHT);
	}

}
