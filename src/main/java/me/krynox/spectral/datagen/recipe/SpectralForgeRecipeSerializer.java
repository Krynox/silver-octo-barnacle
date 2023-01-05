package me.krynox.spectral.datagen.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.krynox.spectral.Spectral;
import me.krynox.spectral.crafting.SpectralForgeRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;


public class SpectralForgeRecipeSerializer implements RecipeSerializer<SpectralForgeRecipe> {
    public SpectralForgeRecipeSerializer() {
    }

    @Override
    public SpectralForgeRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        Map<String, JsonElement> data = pSerializedRecipe.asMap();

        Item result = ForgeRegistries.ITEMS.getValue(Spectral.resLoc(data.get("result").getAsString()));
        Item centralItem = ForgeRegistries.ITEMS.getValue(Spectral.resLoc(data.get("centralItem").getAsString()));
        List<Item> infusionItems = data
                .get("infusionItems")
                .getAsJsonArray()
                .asList()
                .stream()
                .map((e) -> ForgeRegistries.ITEMS.getValue(Spectral.resLoc(e.getAsString())))
                .toList();
        int fireCost = data.get("fireCost").getAsInt();
        int lightningCost = data.get("lightningCost").getAsInt();
        int windCost = data.get("windCost").getAsInt();
        int earthCost = data.get("earthCost").getAsInt();
        int waterCost = data.get("waterCost").getAsInt();
        int iceCost = data.get("iceCost").getAsInt();
        int lightCost = data.get("lightCost").getAsInt();
        int darkCost = data.get("darkCost").getAsInt();

        return new SpectralForgeRecipe(pRecipeId, result, centralItem, infusionItems, fireCost, lightningCost, windCost, earthCost, waterCost, iceCost, lightCost, darkCost);
    }

    @Override
    public @Nullable SpectralForgeRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        return null; //todo - datapacks on servers will probably crash until I do this. servers in general might crash...
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, SpectralForgeRecipe pRecipe) {

    }
}
