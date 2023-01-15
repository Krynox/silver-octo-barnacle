package me.krynox.spectral.client.rendering;

import java.io.IOException;
import java.util.function.Function;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import me.krynox.spectral.Spectral;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderStateShard.ShaderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpectralRenderTypes {
	public static RenderType TEST(ResourceLocation texture) {
		return RenderTypes.TEST.apply(texture); // return the memoized version
	}
	
    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
    	event.registerShader(new ShaderInstance(event.getResourceProvider(), Spectral.resLoc("shaders/test_shader"), DefaultVertexFormat.NEW_ENTITY), 
    			(shaderInstance) -> {
    				RenderTypes.testShader = shaderInstance;
    			});
    }
    
	private static class RenderTypes extends RenderType {
        //dummy constructor, don't use
		private RenderTypes(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2)
        {
            super(s, v, m, i, b, b2, r, r2);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }
		
		//the shader instance. populated after the registered shader is loaded.
		private static ShaderInstance testShader;
		
		private static final ShaderStateShard TEST_SHADER = new ShaderStateShard(() -> testShader);
		
		// The memoized rendertype functions to actually use
        public static Function<ResourceLocation, RenderType> TEST = Util.memoize(RenderTypes::test);
        
        private static RenderType test(ResourceLocation texture) {
        	RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder()
        			.setShaderState(TEST_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                    .setTransparencyState(NO_TRANSPARENCY)
                    .setLightmapState(NO_LIGHTMAP)
                    .setOverlayState(NO_OVERLAY)
                    .createCompositeState(true);
            return create("spectral_test", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, false, rendertype$state);
        }

		
	}
}
