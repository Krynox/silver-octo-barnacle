package me.krynox.spectral.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.spellcaster.ISpellCaster;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.magic.AbstractSpell;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SpellHotbarOverlay implements IGuiOverlay {
	// inner and outer width+height of the slot sprites
	private static final int SLOT_SIZE_INNER = 24;
	private static final int SLOT_SIZE_OUTER = 16;
	private static final ResourceLocation TEXTURE = Spectral.resLoc("textures/gui/spell_bar.png");


	@Override
	public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
		LocalPlayer player = gui.getMinecraft().player;
		if(player != null) {
			player.getCapability(SpectralCapabilities.SPELL_CASTER)
					.resolve()
					.ifPresent((cap) -> {
						if (cap.isSpellcastingMode()) renderBar(poseStack, cap, screenWidth, screenHeight);
					});
		}
	}
	
	private void renderBar(PoseStack poseStack, ISpellCaster cap, int screenWidth, int screenHeight) {
		final int slots = cap.getSpellSlots();
		final int xStart = (screenWidth / 2) - (SLOT_SIZE_INNER * slots / 2);
		final int y = screenHeight - (5 * SLOT_SIZE_INNER / 4);

		RenderSystem.setShaderTexture(0, TEXTURE);
		for(int i = 0; i < slots; i++) {
			renderSlot(poseStack, xStart + (i * SLOT_SIZE_INNER), y);
		}

		for(int i = 0; i < slots; i++) {
			int x = xStart + 4 + (i * SLOT_SIZE_INNER);
			cap.getSpell(i).ifPresent((spell) -> {
				renderSpellIcon(poseStack, spell, x, y + 4);
			});
		}
	}
	
	private void renderSlot(PoseStack poseStack, int x, int y) {  
		GuiComponent.blit(poseStack, x, y, 0, 0, 0, SLOT_SIZE_INNER, SLOT_SIZE_INNER, SLOT_SIZE_INNER, SLOT_SIZE_INNER);
	}

	private void renderSpellIcon(PoseStack poseStack, AbstractSpell spell, int x, int y) {
		ResourceLocation key = Registration.SPELLS_REEGISTRY.get().getKey(spell);
		if(key == null) return;
		ResourceLocation tex = Spectral.resLoc("textures/spell/" + key.getPath() + ".png");

		RenderSystem.setShaderTexture(0, tex);

		GuiComponent.blit(poseStack, x, y, 0, 0, SLOT_SIZE_OUTER, SLOT_SIZE_OUTER, SLOT_SIZE_OUTER, SLOT_SIZE_OUTER);
	}

}
