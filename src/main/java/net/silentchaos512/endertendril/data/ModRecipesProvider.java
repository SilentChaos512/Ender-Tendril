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
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Items.ENDER_EYE)
                .requires(ModItems.TENDRIL_PEARL.get(), 2)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_item", has(ModItems.TENDRIL_PEARL.get()))
                .save(consumer, EnderTendrilMod.getId("ender_eye"));
    }
}
