package net.silentchaos512.endertendril.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider {
    public ModRecipesProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_EYE)
                .requires(ModItems.TENDRIL_PEARL.get(), 2)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_item", has(ModItems.TENDRIL_PEARL.get()))
                .save(consumer, EnderTendrilMod.getId("ender_eye"));
    }
}
