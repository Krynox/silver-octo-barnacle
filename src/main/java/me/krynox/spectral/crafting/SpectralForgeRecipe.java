package me.krynox.spectral.crafting;

import me.krynox.spectral.capability.EctoType;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.util.InvUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpectralForgeRecipe implements Recipe<EctoInvRecipeWrapper> {
    private final ResourceLocation id;

    private final Item result;
    private final Item centralItem;
    private final List<Item> infusionItems;

    private int fireCost = 0;
    private int lightningCost = 0;
    private int windCost = 0;
    private int earthCost = 0;
    private int waterCost = 0;
    private int iceCost = 0;
    private int lightCost = 0;
    private int darkCost = 0;

    public SpectralForgeRecipe(ResourceLocation id, Item result, Item centralItem, List<Item> infusionItems) {
        this.id = id;
        this.result = result;
        this.centralItem = centralItem;
        this.infusionItems = infusionItems;
    }

    @Override
    public boolean matches(EctoInvRecipeWrapper inputs, Level level) {
        return inputs.ectoHandler.get(EctoType.FIRE) >= fireCost
                && inputs.ectoHandler.get(EctoType.LIGHTNING) >= lightningCost
                && inputs.ectoHandler.get(EctoType.WIND) >= windCost
                && inputs.ectoHandler.get(EctoType.EARTH) >= earthCost
                && inputs.ectoHandler.get(EctoType.WATER) >= waterCost
                && inputs.ectoHandler.get(EctoType.ICE) >= iceCost
                && inputs.ectoHandler.get(EctoType.LIGHT) >= lightCost
                && inputs.ectoHandler.get(EctoType.DARK) >= darkCost
                && inputs.getItem(0).is(centralItem)
                && inputs.hasAll(InvUtils.countList(infusionItems));
    }

    //Mutate the state of the container and return the crafting result.
    @Override
    public ItemStack assemble(EctoInvRecipeWrapper inputs) {
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

    /////////////////////////
    // Getters and Setters //
    /////////////////////////

    public Item getResult() {
        return result;
    }

    public Item getCentralItem() {
        return centralItem;
    }

    public List<Item> getInfusionItems() {
        return infusionItems;
    }

    public int getFireCost() {
        return fireCost;
    }

    public int getLightningCost() {
        return lightningCost;
    }

    public int getWindCost() {
        return windCost;
    }

    public int getEarthCost() {
        return earthCost;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public int getIceCost() {
        return iceCost;
    }

    public int getLightCost() {
        return lightCost;
    }

    public int getDarkCost() {
        return darkCost;
    }

    public SpectralForgeRecipe setFireCost(int fireCost) {
        this.fireCost = fireCost;
        return this;
    }

    public SpectralForgeRecipe setLightningCost(int lightningCost) {
        this.lightningCost = lightningCost;
        return this;
    }

    public SpectralForgeRecipe setWindCost(int windCost) {
        this.windCost = windCost;
        return this;
    }

    public SpectralForgeRecipe setEarthCost(int earthCost) {
        this.earthCost = earthCost;
        return this;
    }

    public SpectralForgeRecipe setWaterCost(int waterCost) {
        this.waterCost = waterCost;
        return this;
    }

    public SpectralForgeRecipe setIceCost(int iceCost) {
        this.iceCost = iceCost;
        return this;
    }

    public SpectralForgeRecipe setLightCost(int lightCost) {
        this.lightCost = lightCost;
        return this;
    }

    public SpectralForgeRecipe setDarkCost(int darkCost) {
        this.darkCost = darkCost;
        return this;
    }

}
