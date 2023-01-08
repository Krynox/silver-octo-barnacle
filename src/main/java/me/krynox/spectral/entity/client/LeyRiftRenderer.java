package me.krynox.spectral.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.krynox.spectral.Spectral;
import me.krynox.spectral.entity.LeyRiftEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LeyRiftRenderer extends EntityRenderer<LeyRiftEntity> {
    public LeyRiftRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public boolean shouldRender(LeyRiftEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return super.shouldRender(pLivingEntity, pCamera, pCamX, pCamY, pCamZ);
    }

    @Override
    public void render(LeyRiftEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);

    }

    // probably shouldn't be called, right?
    @Override
    public ResourceLocation getTextureLocation(LeyRiftEntity pEntity) {
        Spectral.LOGGER.error("Tried to get texture location for ley rift renderer, returning null. Something might break.");
        return null;
    }
}
