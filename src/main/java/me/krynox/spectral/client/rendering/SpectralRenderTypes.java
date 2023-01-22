package me.krynox.spectral.client.rendering;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.blaze3d.shaders.Shader;
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
	public static RenderType SPECTRAL_FORGE_PORTAL = RenderTypes.SPECTRAL_FORGE_PORTAL.get();

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
		newSpectralShader(event, "ley_rift", (s) -> RenderTypes.leyRiftShader = s);
		newSpectralShader(event, "spectral_forge_portal", (s) -> RenderTypes.spectralForgePortalShader = s);
    }
    
	private static class RenderTypes extends RenderType {
        //dummy constructor, don't use
		private RenderTypes(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2) {
            super(s, v, m, i, b, b2, r, r2);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }
		
		//the shader instances. populated after the registered shader is loaded.
		private static ShaderInstance leyRiftShader;
		private static ShaderInstance spectralForgePortalShader;

		private static final ShaderStateShard LEY_RIFT_SHADER = new ShaderStateShard(() -> leyRiftShader);
		private static final ShaderStateShard SPECTRAL_FORGE_PORTAL_SHADER = new ShaderStateShard(() -> spectralForgePortalShader);


		// The memoized rendertype functions to actually use. (nb: for rendertypes that take a resourcelocation for a texture,
		// use Util#memoize)
        public static Supplier<RenderType> LEY_RIFT = RenderTypes::leyRift;
		public static Supplier<RenderType> SPECTRAL_FORGE_PORTAL = RenderTypes::spectralForgePortal;

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

		private static RenderType spectralForgePortal() {
			RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder()
					.setShaderState(SPECTRAL_FORGE_PORTAL_SHADER)
					.setTransparencyState(NO_TRANSPARENCY)
					.setLightmapState(NO_LIGHTMAP)
					.setOverlayState(NO_OVERLAY)
					.setCullState(NO_CULL)
					.createCompositeState(true);
			return create("spectral_forge_portal", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, rendertype$state);
		}

	}

	private static void newSpectralShader(RegisterShadersEvent event, String id, Consumer<ShaderInstance> instanceSetter) throws IOException {
		event.registerShader(new ShaderInstance(event.getResourceProvider(), Spectral.resLoc(id), DefaultVertexFormat.NEW_ENTITY), instanceSetter);
	}
}
