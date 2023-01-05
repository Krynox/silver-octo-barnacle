package me.krynox.spectral.crafting;

import me.krynox.spectral.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpectralForgeRecipe implements Recipe<Container> {
    protected final ResourceLocation id;

    private final Item result;
    private final Item centralItem;
    private final List<Item> infusionItems;
    private final int fireCost;
    private final int lightningCost;
    private final int windCost;
    private final int earthCost;
    private final int waterCost;
    private final int iceCost;
    private final int lightCost;
    private final int darkCost;

    public SpectralForgeRecipe(ResourceLocation id, Item result, Item centralItem, List<Item> infusionItems, int fireCost, int lightningCost, int windCost, int earthCost, int waterCost, int iceCost, int lightCost, int darkCost) {
        this.id = id;
        this.result = result;
        this.centralItem = centralItem;
        this.infusionItems = infusionItems;
        this.fireCost = fireCost;
        this.lightningCost = lightningCost;
        this.windCost = windCost;
        this.earthCost = earthCost;
        this.waterCost = waterCost;
        this.iceCost = iceCost;
        this.lightCost = lightCost;
        this.darkCost = darkCost;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return false;
    }

    //Mutate the state of the container and return the crafting result.
    @Override
    public ItemStack assemble(Container pContainer) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(result);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<SpectralForgeRecipe> getSerializer() {
        return Registration.SPECTRAL_FORGE_RECIPESERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return Registration.SPECTRAL_FORGE_RECIPETYPE.get();
    }
}
