package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public final class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ModBlockTagsProvider blocks = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(blocks);
        gen.addProvider(new ModItemTagsProvider(gen, blocks, existingFileHelper));

        gen.addProvider(new ModLootTables(gen));
        gen.addProvider(new ModRecipesProvider(gen));

        gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));
    }
}
