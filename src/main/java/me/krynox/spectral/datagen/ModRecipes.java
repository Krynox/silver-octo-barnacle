package me.krynox.spectral.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(PackOutput p) {
        super(p);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p) {
        //p.accept(SpectralForgeRecipeBuilder.build(.........));
    }
}
