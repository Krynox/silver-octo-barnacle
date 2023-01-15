package me.krynox.spectral.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class FocusBarOverlay implements IGuiOverlay {
	private static final int TEX_WIDTH = 84;
	private static final int TEX_HEIGHT = 12;
	private static final int U_WIDTH = 12;
	private static final int V_HEIGHT = 12;


	@Override
	public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
    	RenderSystem.setShaderTexture(0, Spectral.resLoc("textures/gui/focus_bar.png"));

    	ISpellCaster cap = gui.getMinecraft().player.getCapability(SpectralCapabilities.SPELL_CASTER).resolve().get();
    	
    	if(cap.isSpellcastingMode()) renderBar(poseStack, cap.getFocus(), cap.getMaxFocus(), screenWidth, screenHeight);
	}
	
	private void renderBar(PoseStack poseStack, int focus, int maxFocus, int screenWidth, int screenHeight) {
		int x = Mth.ceil(screenWidth / 2) - (U_WIDTH * maxFocus / 2);
		int y = Mth.ceil(screenHeight * 25 / 32);
		
		for(int i = 0; i < maxFocus; i++) {
			renderPip(poseStack, pipToRender(i, focus, maxFocus), x, y);
			x += 12;
		}
	}
	
	private PipType pipToRender(int i, int focus, int maxFocus) {
		if(maxFocus % 2 == 0) {
			// max focus is expected to always be odd. 
			// can add logic in here later if we add the ability for the player to increase the max.
			return PipType.EMPTY_SMALL; 
		} else {
			if(i == maxFocus - 1) {
				if(focus > i) {
					return PipType.FILLED_LARGE_3;
				} else {
					return PipType.EMPTY_LARGE;
				}
			} else if(i > maxFocus / 2) {
				if(focus > i) {
					return PipType.FILLED_SMALL_2;
				} else {
					return PipType.EMPTY_SMALL;
				}
			} else if (i == maxFocus / 2) {
				if(focus > i) {
					return PipType.FILLED_LARGE_2;
				} else {
					return PipType.EMPTY_LARGE;
				}
			} else if(i > 0) {
				if(focus > i) {
					return PipType.FILLED_SMALL_1;
				} else {
					return PipType.EMPTY_SMALL;
				}
			} else if(i == 0) {
				if(focus > i) {
					return PipType.FILLED_LARGE_1;
				} else {
					return PipType.EMPTY_LARGE;
				}
			} else {
				return PipType.EMPTY_SMALL;
			}
		}
	}
	
	private void renderPip(PoseStack poseStack, PipType pipType, int x, int y) {  
		int blitOffset = 0;
		int uOffset = pipType.getU();
		int vOffset = 0;

		GuiComponent.blit(poseStack, x, y, blitOffset, uOffset, vOffset, U_WIDTH, V_HEIGHT, TEX_WIDTH, TEX_HEIGHT);
	}
	
	public static enum PipType {
		FILLED_LARGE_1(0),
		FILLED_LARGE_2(1),
		FILLED_LARGE_3(2),
		FILLED_SMALL_1(3),
		FILLED_SMALL_2(4),
		EMPTY_LARGE(5),
		EMPTY_SMALL(6);
		
		private final int index;
		
		PipType(int i) {
			this.index = i;
		}
		
		int getU() {
			return 12 * index;
		}
	}

}
