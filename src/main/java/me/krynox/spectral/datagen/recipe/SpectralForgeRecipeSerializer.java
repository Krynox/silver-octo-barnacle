package me.krynox.spectral.datagen.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.krynox.spectral.crafting.SpectralForgeRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class SpectralForgeRecipeSerializer implements RecipeSerializer<SpectralForgeRecipe> {
    public SpectralForgeRecipeSerializer() {
    }

    @Override
    public SpectralForgeRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        Map<String, JsonElement> data = pSerializedRecipe.asMap();

        Item result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.get("result").getAsString()));
        Item centralItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.get("input").getAsString()));

        SpectralForgeRecipe output = new SpectralForgeRecipe(pRecipeId, result, centralItem);

        output.setFireCost(data.get("fireCost").getAsInt());
        output.setLightningCost(data.get("lightningCost").getAsInt());
        output.setWindCost(data.get("windCost").getAsInt());
        output.setEarthCost(data.get("earthCost").getAsInt());
        output.setWaterCost(data.get("waterCost").getAsInt());
        output.setIceCost(data.get("iceCost").getAsInt());
        output.setLightCost(data.get("lightCost").getAsInt());
        output.setDarkCost(data.get("darkCost").getAsInt());

        return output;
    }

    @Override
    public @Nullable SpectralForgeRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {

        Item result = pBuffer.readRegistryIdSafe(Item.class);
        Item input = pBuffer.readRegistryIdSafe(Item.class);

        SpectralForgeRecipe output = new SpectralForgeRecipe(pRecipeId, result, input);

        output.setFireCost(pBuffer.readInt());
        output.setLightningCost(pBuffer.readInt());
        output.setWindCost(pBuffer.readInt());
        output.setEarthCost(pBuffer.readInt());
        output.setWaterCost(pBuffer.readInt());
        output.setIceCost(pBuffer.readInt());
        output.setLightCost(pBuffer.readInt());
        output.setDarkCost(pBuffer.readInt());

        return output;
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, SpectralForgeRecipe pRecipe) {
        pBuffer.writeRegistryId(ForgeRegistries.ITEMS, pRecipe.getResult());
        pBuffer.writeRegistryId(ForgeRegistries.ITEMS, pRecipe.getInput());

        pBuffer.writeInt(pRecipe.getFireCost());
        pBuffer.writeInt(pRecipe.getLightningCost());
        pBuffer.writeInt(pRecipe.getWindCost());
        pBuffer.writeInt(pRecipe.getEarthCost());
        pBuffer.writeInt(pRecipe.getWaterCost());
        pBuffer.writeInt(pRecipe.getIceCost());
        pBuffer.writeInt(pRecipe.getLightCost());
        pBuffer.writeInt(pRecipe.getDarkCost());
    }
}
