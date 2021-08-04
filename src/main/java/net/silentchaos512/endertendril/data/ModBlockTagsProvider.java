package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, EnderTendrilMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(ModTags.Blocks.ENDER_TENDRILS)
                .add(ModBlocks.ENDER_TENDRIL.get())
                .add(ModBlocks.ENDER_TENDRIL_PLANT.get())
                .add(ModBlocks.FLOWERING_ENDER_TENDRIL.get());

        tag(BlockTags.CLIMBABLE)
            .addTag(ModTags.Blocks.ENDER_TENDRILS);
    }
}
