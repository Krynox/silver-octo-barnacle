package me.krynox.spectral.entity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import me.krynox.spectral.Spectral;
import me.krynox.spectral.client.rendering.SpectralRenderTypes;
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


    public LeyRiftRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public boolean shouldRender(LeyRiftEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true; //this is dumb and needs to change, but one thing at a time 
    }

    @Override
    public void render(LeyRiftEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    	pPoseStack.pushPose();
    	
    	RenderType rt = SpectralRenderTypes.TEST(new ResourceLocation("minecraft:textures/block/dirt.png"));
    	rt.setupRenderState();
    	VertexConsumer buf = pBuffer.getBuffer(rt);
    	
    	//buf.vertex(-0.5f, -0.5f, 0, 1, 0.5f, 0.5f, 1, 0, 0, 0, 0, 0, 0, 1);
    	//buf.vertex(-0.5f, 0.5f, 0, 1, 0.5f, 0.5f, 1, 0, 1, 0, 0, 0, 0, 1);
    	//buf.vertex(0.5f, 0.5f, 0, 1, 0.5f, 0.5f, 1, 1, 1, 0, 0, 0, 0, 1);
    	//buf.vertex(0.5f, -0.5f, 0, 1, 0.5f, 0.5f, 1, 1, 0, 0, 0, 0, 0, 1);
    	pPoseStack.popPose();
    	
    	//super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    // probably shouldn't be called, right?
    @Override
    public ResourceLocation getTextureLocation(LeyRiftEntity pEntity) {
        Spectral.LOGGER.error("Tried to get texture location for ley rift renderer, returning null. Something might break.");
        return null;
    }
}
