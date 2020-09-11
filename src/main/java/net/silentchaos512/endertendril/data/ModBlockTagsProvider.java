package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

public class ModBlockTagsProvider extends ForgeBlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, existingFileHelper);
    }

    @Override
    public void registerTags() {
        getOrCreateBuilder(ModTags.Blocks.ENDER_TENDRILS)
                .add(ModBlocks.ENDER_TENDRIL.get())
                .add(ModBlocks.ENDER_TENDRIL_PLANT.get())
                .add(ModBlocks.FLOWERING_ENDER_TENDRIL.get());

        getOrCreateBuilder(BlockTags.CLIMBABLE)
            .addTag(ModTags.Blocks.ENDER_TENDRILS);
    }
}
