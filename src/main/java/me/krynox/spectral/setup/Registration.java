package me.krynox.spectral.setup;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.block.SoulMirror;
import me.krynox.spectral.block.SpectralForge;
import me.krynox.spectral.block.entity.SoulMirrorBE;
import me.krynox.spectral.block.entity.SpectralForgeBE;
import me.krynox.spectral.crafting.SpectralForgeRecipe;
import me.krynox.spectral.datagen.recipe.SpectralForgeRecipeSerializer;
import me.krynox.spectral.entity.SpiritEntity;
import me.krynox.spectral.item.SpectralMonocle;
import me.krynox.spectral.item.SpiritCrystal;
import me.krynox.spectral.spell.Spell;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Spectral.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Spectral.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Spectral.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Spectral.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Spectral.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Spectral.MODID);

    //Custom registries; a DeferredRegister to use in this class, and then the actual registry constructed from it.
    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(Spectral.resLoc("spells_registy"), Spectral.MODID);
    public static final Supplier<IForgeRegistry<Spell>> SPELLS_REEGISTRY = SPELLS.makeRegistry(RegistryBuilder::new);

    public static void registerAll(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
        BLOCK_ENTITIES.register(bus);
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        SPELLS.register(bus);
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

    //////////////////////
    //// RECIPE STUFF ////
    //////////////////////

    public static final RegistryObject<RecipeType<SpectralForgeRecipe>> SPECTRAL_FORGE_RECIPETYPE =
            RECIPE_TYPES.register("spectral_forge_recipetype", () ->
                    RecipeType.simple(Spectral.resLoc("spectral_forge_recipetype")));

    public static final RegistryObject<SpectralForgeRecipeSerializer> SPECTRAL_FORGE_RECIPESERIALIZER =
            RECIPE_SERIALIZERS.register("spectral_forge_recipeserializer", SpectralForgeRecipeSerializer::new);

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

    public static final RegistryObject<Block> SOUL_MIRROR_BLOCK
            = BLOCKS.register("soul_mirror", SoulMirror::new);
    public static final RegistryObject<Item> SOUL_MIRROR_ITEM
            = fromBlock(SOUL_MIRROR_BLOCK);
    public static final RegistryObject<BlockEntityType<SoulMirrorBE>> SOUL_MIRROR_BE
            = BLOCK_ENTITIES.register("soul_mirror", () -> BlockEntityType.Builder
            .of(SoulMirrorBE::new, SOUL_MIRROR_BLOCK.get())
            .build(null)); //passing null to opt out of datafixers

    public static final RegistryObject<Block> SPECTRAL_FORGE_BLOCK
            = BLOCKS.register("spectral_forge", SpectralForge::new);
    public static final RegistryObject<Item> SPECTRAL_FORGE_ITEM
            = fromBlock(SPECTRAL_FORGE_BLOCK);
    public static final RegistryObject<BlockEntityType<SpectralForgeBE>> SPECTRAL_FORGE_BE
            = BLOCK_ENTITIES.register("spectral_forge", () -> BlockEntityType.Builder
            .of(SpectralForgeBE::new, SPECTRAL_FORGE_BLOCK.get())
            .build(null));

    //////////////////
    //// ENTITIES ////
    //////////////////

    // NB: Don't forget to also register EntityAttributes in CommonSetup for LivingEntities.

    public static final RegistryObject<EntityType<SpiritEntity>> SPIRIT_ENTITY
            = ENTITIES.register("spirit", () -> EntityType.Builder.of(SpiritEntity::new, MobCategory.MISC)
            .sized(1f,1f)
            .build("spirit"));

    public static final RegistryObject<ForgeSpawnEggItem> SPIRIT_SPAWN_EGG_ITEM
            = ITEMS.register("spirit_spawn_egg", () -> new ForgeSpawnEggItem(SPIRIT_ENTITY, 0xaaffaa, 0xffaaaa, DEFAULT_ITEM_PROPERTIES));

    ////////////////
    //// SPELLS ////
    ////////////////

    public static final RegistryObject<Spell> TEST_SPELL
        = SPELLS.register("test_spell", Spell::new);
}
