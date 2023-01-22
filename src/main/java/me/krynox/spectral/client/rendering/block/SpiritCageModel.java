package me.krynox.spectral.client.rendering.block;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.entity.SpiritCageBE;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SpiritCageModel extends GeoModel<SpiritCageBE> {
    @Override
    public ResourceLocation getModelResource(SpiritCageBE animatable) {
        return Spectral.resLoc("geo/block/spirit_cage.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritCageBE animatable) {
        return Spectral.resLoc("textures/block/spirit_cage.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritCageBE animatable) {
        return Spectral.resLoc("animations/block/spirit_cage.animation.json");
    }
}
