package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider {
    public ModRecipesProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(Items.ENDER_EYE)
                .addIngredient(ModItems.TENDRIL_PEARL.get(), 2)
                .addIngredient(Items.BLAZE_POWDER)
                .addCriterion("has_item", hasItem(ModItems.TENDRIL_PEARL.get()))
                .build(consumer, EnderTendrilMod.getId("ender_eye"));
    }
}
