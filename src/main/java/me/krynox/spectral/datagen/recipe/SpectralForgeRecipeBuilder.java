package me.krynox.spectral.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class SpectralForgeRecipeBuilder implements RecipeBuilder {
    private final ResourceLocation recipeId;
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

    private SpectralForgeRecipeBuilder(ResourceLocation recipeId, Item result, Item input) {
        this.recipeId = recipeId;
        this.result = result;
        this.input = input;
    }

    public static SpectralForgeRecipeBuilder build(ResourceLocation recipeId, Item result, Item input) {
        return new SpectralForgeRecipeBuilder(recipeId, result, input);
    }

    public SpectralForgeRecipeBuilder setEctoCost(MagicType type, int cost) {
        switch (type) {
            case FIRE -> {
                fireCost = cost;
            }
            case LIGHTNING -> {
                lightningCost = cost;
            }
            case WIND -> {
                windCost = cost;
            }
            case EARTH -> {
                earthCost = cost;
            }
            case WATER -> {
                waterCost = cost;
            }
            case ICE -> {
                iceCost = cost;
            }
            case LIGHT -> {
                lightCost = cost;
            }
            case DARK -> {
                darkCost = cost;
            }
        }
        return this;
    }

    public FinishedRecipe finish() {
        return new FinishedRecipe() {
            @Override
            public void serializeRecipeData(JsonObject pJson) {

                pJson.addProperty("result", ForgeRegistries.ITEMS.getKey(result).toString());
                pJson.addProperty("input", ForgeRegistries.ITEMS.getKey(input).toString());
                pJson.addProperty("fireCost", fireCost);
                pJson.addProperty("lightningCost", lightningCost);
                pJson.addProperty("windCost", windCost);
                pJson.addProperty("earthCost", earthCost);
                pJson.addProperty("waterCost", waterCost);
                pJson.addProperty("iceCost", iceCost);
                pJson.addProperty("lightCost", lightCost);
                pJson.addProperty("darkCost", darkCost);
            }

            @Override
            public ResourceLocation getId() {
                return recipeId;
            }

            @Override
            public RecipeSerializer<?> getType() {
                return Registration.SPECTRAL_FORGE_RECIPESERIALIZER.get();
            }

            @Nullable
            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }

            @Nullable
            @Override
            public ResourceLocation getAdvancementId() {
                return null;
            }
        };
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(finish());
    }
}
