package me.krynox.spectral.crafting;

import me.krynox.spectral.magic.MagicType;
import me.krynox.spectral.content.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SpectralForgeRecipe implements Recipe<EctoInvRecipeWrapper> {
    private final ResourceLocation id;

    private final Item result;
    private final Item input;

    private int fireCost = 0;
    private int lightningCost = 0;
    private int windCost = 0;
    private int earthCost = 0;
    private int waterCost = 0;
    private int iceCost = 0;
    private int lightCost = 0;
    private int darkCost = 0;

    public SpectralForgeRecipe(ResourceLocation id, Item result, Item input) {
        this.id = id;
        this.result = result;
        this.input = input;
    }

    // takes level because the override does, but we don't care about it here
    @Override
    public boolean matches(EctoInvRecipeWrapper state, @Nullable Level level) {
        return state.ectoHandler.get(MagicType.FIRE) >= fireCost
                && state.ectoHandler.get(MagicType.LIGHTNING) >= lightningCost
                && state.ectoHandler.get(MagicType.WIND) >= windCost
                && state.ectoHandler.get(MagicType.EARTH) >= earthCost
                && state.ectoHandler.get(MagicType.WATER) >= waterCost
                && state.ectoHandler.get(MagicType.ICE) >= iceCost
                && state.ectoHandler.get(MagicType.LIGHT) >= lightCost
                && state.ectoHandler.get(MagicType.DARK) >= darkCost
                && state.getItem(0).is(input);
    }

    //Mutate the state of the container and return the crafting result.
    @Override
    public ItemStack assemble(EctoInvRecipeWrapper state) {
        if(!matches(state, null)) return ItemStack.EMPTY;

        ItemStack result = getResultItem();

        state.removeItem(0,1);
        state.insertItem(result);

        state.ectoHandler.add(MagicType.FIRE, -fireCost);
        state.ectoHandler.add(MagicType.LIGHTNING, -lightningCost);
        state.ectoHandler.add(MagicType.WIND, -windCost);
        state.ectoHandler.add(MagicType.EARTH, -earthCost);
        state.ectoHandler.add(MagicType.WATER, -waterCost);
        state.ectoHandler.add(MagicType.ICE, -iceCost);
        state.ectoHandler.add(MagicType.LIGHT, -lightCost);
        state.ectoHandler.add(MagicType.DARK, -darkCost);

        return result;
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

    public Item getInput() {
        return input;
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
