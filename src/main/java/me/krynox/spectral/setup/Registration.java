package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.LeyConduit;
import me.krynox.spectral.block.SoulMirror;
import me.krynox.spectral.block.SpectralForge;
import me.krynox.spectral.block.SpiritLocus;
import me.krynox.spectral.block.entity.LeyConduitBE;
import me.krynox.spectral.item.SpectralMonocle;
import me.krynox.spectral.item.SpiritCrystal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Spectral.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Spectral.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Spectral.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Spectral.MODID);

    public static void registerAll(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    public static final BlockBehaviour.Properties DEFAULT_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
    public static final Item.Properties DEFAULT_ITEM_PROPERTIES = new Item.Properties();

    //////////////////////////
    //// HELPER FUNCTIONS ////
    //////////////////////////

    /**
     * Create a default BlockItem for a given Block.
     */
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), DEFAULT_ITEM_PROPERTIES));
    }

    public static RegistryObject<Item> newDefaultItem(String name) {
        return ITEMS.register(name, () -> new Item(DEFAULT_ITEM_PROPERTIES));
    }

    /////////////////////////////////////////////
    ///////// Registration Starts Here //////////

    ///////////////
    //// ITEMS ////
    ///////////////

    public static final RegistryObject<Item> SPECTRAL_MONOCLE_ITEM
            = ITEMS.register("spectral_monocle", SpectralMonocle::new);

    public static final RegistryObject<Item> SPIRIT_CRYSTAL_ITEM
            = ITEMS.register("spirit_crystal", SpiritCrystal::new);

    public static final RegistryObject<Item> FIRE_ECTO_ITEM
            = newDefaultItem("fire_ecto");
    public static final RegistryObject<Item> LIGHTNING_ECTO_ITEM
            = newDefaultItem("lightning_ecto");
    public static final RegistryObject<Item> WIND_ECTO_ITEM
            = newDefaultItem("wind_ecto");
    public static final RegistryObject<Item> EARTH_ECTO_ITEM
            = newDefaultItem("earth_ecto");
    public static final RegistryObject<Item> WATER_ECTO_ITEM
            = newDefaultItem("water_ecto");
    public static final RegistryObject<Item> ICE_ECTO_ITEM
            = newDefaultItem("ice_ecto");
    public static final RegistryObject<Item> DARK_ECTO_ITEM
            = newDefaultItem("dark_ecto");
    public static final RegistryObject<Item> LIGHT_ECTO_ITEM
            = newDefaultItem("light_ecto");

    /////////////////////////////////////////////////
    //// BLOCKS, BLOCK_ITEMS, BLOCK_ENTITY_TYPES ////
    /////////////////////////////////////////////////

    public static final RegistryObject<Block> LEY_CONDUIT_BLOCK
            = BLOCKS.register("ley_conduit", LeyConduit::new);
    public static final RegistryObject<Item> LEY_CONDUIT_ITEM
            = fromBlock(LEY_CONDUIT_BLOCK);
    public static final RegistryObject<BlockEntityType<LeyConduitBE>> LEY_CONDUIT_BE
            = BLOCK_ENTITIES.register("ley_conduit", () -> BlockEntityType.Builder
                    .of(LeyConduitBE::new, LEY_CONDUIT_BLOCK.get())
                    .build(null)); //null type for the datafixer

    public static final RegistryObject<Block> SOUL_MIRROR_BLOCK
            = BLOCKS.register("soul_mirror", SoulMirror::new);
    public static final RegistryObject<Item> SOUL_MIRROR_ITEM
            = fromBlock(SOUL_MIRROR_BLOCK);

    public static final RegistryObject<Block> SPECTRAL_FORGE_BLOCK
            = BLOCKS.register("spectral_forge", SpectralForge::new);
    public static final RegistryObject<Item> SPECTRAL_FORGE_ITEM
            = fromBlock(SPECTRAL_FORGE_BLOCK);

    public static final RegistryObject<Block> SPIRIT_LOCUS_BLOCK
            = BLOCKS.register("spirit_locus", SpiritLocus::new);
    public static final RegistryObject<Item> SPIRIT_LOCUS_ITEM
            = fromBlock(SPIRIT_LOCUS_BLOCK);

    //////////////////
    //// ENTITIES ////
    //////////////////

    // NB: Don't forget to also register EntityAttributes in CommonSetup for LivingEntities.


}
