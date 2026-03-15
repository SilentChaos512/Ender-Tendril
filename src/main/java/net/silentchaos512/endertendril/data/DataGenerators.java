package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.data.client.ModModelProvider;
import net.silentchaos512.endertendril.data.tag.ModBlockTagsProvider;
import net.silentchaos512.endertendril.data.tag.ModItemTagsProvider;
import net.silentchaos512.endertendril.loot.ChestInjectorLootModifier;
import net.silentchaos512.lib.data.recipe.LibRecipeProvider;

public final class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        gen.addProvider(true, new ModBlockTagsProvider(event));
        gen.addProvider(true, new ModItemTagsProvider(event));

        gen.addProvider(true, new ModLootTables(packOutput, lookupProvider));
        gen.addProvider(true, LibRecipeProvider.createRunner(packOutput, lookupProvider, "Ender Tendril Recipes", ModRecipesProvider::new));

        gen.addProvider(true, new ModModelProvider(packOutput));

        gen.addProvider(true, new GlobalLootModifierProvider(packOutput, lookupProvider, EnderTendrilMod.MOD_ID) {
            @Override
            protected void start() {
                add("chest_loot_injector", new ChestInjectorLootModifier(new LootItemCondition[]{}));
            }
        });

        gen.addProvider(true, new ModWorldGenGenerator(packOutput, lookupProvider).createRunner());
    }
}
