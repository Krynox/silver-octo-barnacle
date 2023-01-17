package me.krynox.spectral.client.rendering;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import me.krynox.spectral.Spectral;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderStateShard.ShaderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SpectralRenderTypes {
	public static RenderType LEY_RIFT = RenderTypes.LEY_RIFT.get();

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
    	event.registerShader(new ShaderInstance(event.getResourceProvider(), Spectral.resLoc("ley_rift"), DefaultVertexFormat.NEW_ENTITY),
    			(shaderInstance) -> {
    				RenderTypes.leyRiftShader = shaderInstance;
    			});
    }
    
	private static class RenderTypes extends RenderType {
        //dummy constructor, don't use
		private RenderTypes(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2) {
            super(s, v, m, i, b, b2, r, r2);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }
		
		//the shader instance. populated after the registered shader is loaded.
		private static ShaderInstance leyRiftShader;
		
		private static final ShaderStateShard LEY_RIFT_SHADER = new ShaderStateShard(() -> leyRiftShader);
		
		// The memoized rendertype functions to actually use. (nb: for rendertypes that take a resourcelocation for a texture,
		// use Util#memoize)
        public static Supplier<RenderType> LEY_RIFT = RenderTypes::leyRift;
        
        private static RenderType leyRift() {
        	RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder()
        			.setShaderState(LEY_RIFT_SHADER)
                    .setTransparencyState(NO_TRANSPARENCY)
                    .setLightmapState(NO_LIGHTMAP)
                    .setOverlayState(NO_OVERLAY)
					.setCullState(NO_CULL)
                    .createCompositeState(true);
            return create("ley_rift", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, rendertype$state);
        }

		
	}
}
