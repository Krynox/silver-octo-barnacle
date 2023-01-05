package me.krynox.spectral.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.krynox.spectral.setup.Registration;
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

    private SpectralForgeRecipeBuilder(Item result, Item centralItem, List<Item> infusionItems, int fireCost, int lightningCost, int windCost, int earthCost, int waterCost, int iceCost, int lightCost, int darkCost) {
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

    public static FinishedRecipe build(ResourceLocation recipeId, Item result, Item centralItem, List<Item> infusionItems, int fireCost, int lightningCost, int windCost, int earthCost, int waterCost, int iceCost, int lightCost, int darkCost) {
        SpectralForgeRecipeBuilder b = new SpectralForgeRecipeBuilder(result, centralItem, infusionItems, fireCost, lightningCost, windCost, earthCost, waterCost, iceCost, lightCost, darkCost);
        return b.finish(recipeId);
    }

    public FinishedRecipe finish(ResourceLocation recipeId) {
        return new FinishedRecipe() {
            @Override
            public void serializeRecipeData(JsonObject pJson) {

                pJson.addProperty("result", ForgeRegistries.ITEMS.getKey(result).toString());
                pJson.addProperty("centralItem", ForgeRegistries.ITEMS.getKey(centralItem).toString());
                pJson.add("infusionItems", infusionItems
                        .stream()
                        .collect(
                                JsonArray::new,
                                (arr, item) -> {
                                    arr.add(ForgeRegistries.ITEMS.getKey(item).toString());
                                },
                                JsonArray::addAll));
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
        pFinishedRecipeConsumer.accept(finish(pRecipeId));
    }
}
