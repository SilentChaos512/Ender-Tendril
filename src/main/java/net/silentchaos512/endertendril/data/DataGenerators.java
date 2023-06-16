package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.loot.ChestInjectorLootModifier;

public final class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ModBlockTagsProvider blocks = new ModBlockTagsProvider(event);
        gen.addProvider(true, blocks);
        gen.addProvider(true, new ModItemTagsProvider(event, blocks));

        gen.addProvider(true, new ModLootTables(gen.getPackOutput()));
        gen.addProvider(true, new ModRecipesProvider(gen.getPackOutput()));

        gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper));

        gen.addProvider(true, new GlobalLootModifierProvider(gen.getPackOutput(), EnderTendrilMod.MOD_ID) {
            @Override
            protected void start() {
                add("chest_loot_injector", new ChestInjectorLootModifier(new LootItemCondition[]{}));
            }
        });
    }
}
