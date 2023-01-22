package me.krynox.spectral.client.rendering.block;

import me.krynox.spectral.block.entity.SpectralForgeBE;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SpectralForgeRenderer extends GeoBlockRenderer<SpectralForgeBE> {
    public SpectralForgeRenderer() {
        super(new SpectralForgeModel());
    }
}
