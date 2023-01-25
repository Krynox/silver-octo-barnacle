package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.datagen.recipe.SpectralForgeRecipeBuilder;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.magic.MagicType;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(PackOutput p) {
        super(p);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p) {
        //p.accept(SpectralForgeRecipeBuilder.build(.........));
        p.accept(SpectralForgeRecipeBuilder.build(Spectral.resLoc("spell_crystal"), Registration.SPIRIT_CRYSTAL_ITEM.get(), Items.AMETHYST_SHARD).setEctoCost(MagicType.FIRE, 3).finish());
    }
}
