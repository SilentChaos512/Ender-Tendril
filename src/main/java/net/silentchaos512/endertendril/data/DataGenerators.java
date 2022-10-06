package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public final class DataGenerators {
    private DataGenerators() {}

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ModBlockTagsProvider blocks = new ModBlockTagsProvider(gen, existingFileHelper);
        gen.addProvider(true, blocks);
        gen.addProvider(true, new ModItemTagsProvider(gen, blocks, existingFileHelper));

        gen.addProvider(true, new ModLootTables(gen));
        gen.addProvider(true, new ModRecipesProvider(gen));

        gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper));
    }
}
