package me.krynox.spectral.entity.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import me.krynox.spectral.Spectral;
import me.krynox.spectral.entity.LeyRiftEntity;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LeyRiftRenderer extends EntityRenderer<LeyRiftEntity> {


    // Copying in the RenderType for the end portal as a placeholder
    private static final RenderStateShard.ShaderStateShard SHADER
            = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEndPortalShader);

    private static final RenderType RENDER_TYPE
            = RenderType
                .create("end_portal",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(SHADER)
                            .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                                    .add(TheEndPortalRenderer.END_SKY_LOCATION, false, false)
                                    .add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false)
                                    .build())
                            .createCompositeState(false));

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
