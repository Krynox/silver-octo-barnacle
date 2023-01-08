package me.krynox.spectral.entity.client;

import me.krynox.spectral.entity.SpiritEntity;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpiritRenderer extends GeoEntityRenderer<SpiritEntity> {
    public SpiritRenderer(EntityRendererProvider.Context renderManager, MagicType type) {
        super(renderManager, new SpiritModel(type));
    }
}
