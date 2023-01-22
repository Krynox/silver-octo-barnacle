package me.krynox.spectral.client.rendering.entity;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.krynox.spectral.Spectral;
import me.krynox.spectral.client.rendering.SpectralRenderTypes;
import me.krynox.spectral.entity.LeyRiftEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2f;

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

        Entity camera = Minecraft.getInstance().cameraEntity;

        // the vector between the player and the rift
        Vec3 toRift = camera.position().subtract(pEntity.position());
        Vector2f toRiftXZ = new Vector2f((float) toRift.x, (float) toRift.z);

        //the same for the previous tick
        Vector2f toRiftOld = new Vector2f((float) camera.xOld - (float) pEntity.xOld, (float) camera.zOld - (float) pEntity.zOld);

        // the XZ components of the normal vector of the quad that we draw.
        // ie, because it's coplanar with the XY plane, its normal is the Z axis
        Vector2f normal = new Vector2f(0,1);

        // the angle to rotate the plane by is the angle between these two vectors.
        // except the position vectors only update every tick, while we need to draw every frame.
        // so also get the angle for last tick, and lerp between them
        float angleOld = toRiftOld.angle(normal);
        float angleNew = toRiftXZ.angle(normal);
        float diff = angleOld - angleNew;
        if(diff > 1) {
            angleNew += Math.PI * 2;
        } else if (diff < -1) {
            angleOld += Math.PI * 2;
        }
        float angle = Math.lerp(angleOld, angleNew, pPartialTick);

        //rotate around the z axis so that the plane always faces the player
        pPoseStack.mulPose(new Quaternionf().rotateLocalY(angle));

    	RenderType rt = SpectralRenderTypes.LEY_RIFT;
    	VertexConsumer buf = pBuffer.getBuffer(rt);

        Matrix4f mat = pPoseStack.last().pose();
        buf.vertex(mat, -1, 0, 0).endVertex();
        buf.vertex(mat, -1, 2, 0).endVertex();
        buf.vertex(mat, 1, 2, 0).endVertex();
        buf.vertex(mat, 1, 0, 0).endVertex();

    	pPoseStack.popPose();
    }

    //it doesn't have a texture, so the superclass insisting on this being defined is weird.
    @Override
    public ResourceLocation getTextureLocation(LeyRiftEntity pEntity) {
        Spectral.LOGGER.error("Tried to get texture location for ley rift renderer, returning null. Something might break.");
        return null;
    }
}
