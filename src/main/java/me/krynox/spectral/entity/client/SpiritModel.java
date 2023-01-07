package me.krynox.spectral.entity.client;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.entity.SpiritEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SpiritModel extends GeoModel<SpiritEntity> {
    @Override
    public ResourceLocation getModelResource(SpiritEntity animatable) {
        return Spectral.resLoc("geo/spirit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritEntity animatable) {
        return Spectral.resLoc("textures/entity/spirit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritEntity animatable) {
        return Spectral.resLoc("animations/spirit.animation.json");
    }
}
