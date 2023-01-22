package me.krynox.spectral.client.rendering.block;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.entity.SpectralForgeBE;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SpectralForgeModel extends GeoModel<SpectralForgeBE> {
    @Override
    public ResourceLocation getModelResource(SpectralForgeBE animatable) {
        return Spectral.resLoc("geo/block/spectral_forge.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpectralForgeBE animatable) {
        return Spectral.resLoc("textures/block/spectral_forge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpectralForgeBE animatable) {
        return Spectral.resLoc("animations/block/spectral_forge.animation.json");
    }
}
