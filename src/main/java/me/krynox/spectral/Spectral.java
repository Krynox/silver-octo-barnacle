package me.krynox.spectral;

import com.mojang.logging.LogUtils;
import me.krynox.spectral.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Spectral.MODID)
public class Spectral
{
    public static final String MODID = "spectral";
    public static final Logger LOGGER = LogUtils.getLogger();


    public Spectral()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.registerAll(modEventBus);
    }

    /**
     * Get a new ResourceLocation namespaced to this mod.
     */
    public static ResourceLocation resLoc(String name) {
        return new ResourceLocation(MODID, name);
    }
}
