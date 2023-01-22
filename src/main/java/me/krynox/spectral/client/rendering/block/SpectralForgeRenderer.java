package me.krynox.spectral.client.rendering.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.entity.SpectralForgeBE;
import me.krynox.spectral.client.rendering.SpectralRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SpectralForgeRenderer extends GeoBlockRenderer<SpectralForgeBE> {
    public SpectralForgeRenderer() {
        super(new SpectralForgeModel());
    }

    @Override
    public void actuallyRender(PoseStack poseStack, SpectralForgeBE animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);


        if(animatable.isActive()) {
            poseStack.pushPose();

            RenderType rt = SpectralRenderTypes.SPECTRAL_FORGE_PORTAL;
            VertexConsumer buf = bufferSource.getBuffer(rt);

            float y = 0.5f - animatable.getPortalLayer();

            Matrix4f mat = poseStack.last().pose();
            buf.vertex(mat, -2, y, -2).endVertex();
            buf.vertex(mat, -2, y, 3).endVertex();
            buf.vertex(mat, 3, y, 3).endVertex();
            buf.vertex(mat, 3, y, -2).endVertex();

            poseStack.popPose();
        }
    }
}
