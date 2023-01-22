package me.krynox.spectral.client.rendering.entity;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.entity.SpiritEntity;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SpiritModel extends GeoModel<SpiritEntity> {
    private String type;
    public SpiritModel(MagicType type) {
        this.type = type.toString().toLowerCase();
    }
    @Override
    public ResourceLocation getModelResource(SpiritEntity animatable) {
        return Spectral.resLoc("geo/spirit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritEntity animatable) {
        return Spectral.resLoc("textures/entity/" + type + "_spirit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritEntity animatable) {
        return Spectral.resLoc("animations/spirit.animation.json");
    }
}
