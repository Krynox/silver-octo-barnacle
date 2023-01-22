package me.krynox.spectral.client.rendering.block;

import me.krynox.spectral.block.entity.SpiritCageBE;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SpiritCageRenderer extends GeoBlockRenderer<SpiritCageBE> {
    public SpiritCageRenderer() {
        super(new SpiritCageModel());
    }
}
