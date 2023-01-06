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
                .asList() // why doesn't it have a stream() method of its own :(
                .stream()
                .map((e) -> ForgeRegistries.ITEMS.getValue(Spectral.resLoc(e.getAsString())))
                .toList();

        SpectralForgeRecipe output = new SpectralForgeRecipe(pRecipeId, result, centralItem, infusionItems);

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
        Item centralItem = pBuffer.readRegistryIdSafe(Item.class);
        List<Item> infusionItems = pBuffer.readList((buf) -> buf.readRegistryIdSafe(Item.class));

        SpectralForgeRecipe output = new SpectralForgeRecipe(pRecipeId, result, centralItem, infusionItems);

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
        pBuffer.writeRegistryId(ForgeRegistries.ITEMS, pRecipe.getCentralItem());
        pBuffer.writeCollection(pRecipe.getInfusionItems(), (buf, item) -> buf.writeRegistryId(ForgeRegistries.ITEMS, item));

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
