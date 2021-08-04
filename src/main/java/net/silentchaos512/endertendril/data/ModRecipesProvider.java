package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider {
    public ModRecipesProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Items.ENDER_EYE)
                .requires(ModItems.TENDRIL_PEARL.get(), 2)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_item", has(ModItems.TENDRIL_PEARL.get()))
                .save(consumer, EnderTendrilMod.getId("ender_eye"));
    }
}
