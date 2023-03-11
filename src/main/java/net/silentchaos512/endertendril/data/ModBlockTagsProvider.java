package net.silentchaos512.endertendril.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), EnderTendrilMod.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Blocks.ENDER_TENDRILS)
                .add(ModBlocks.ENDER_TENDRIL.get())
                .add(ModBlocks.ENDER_TENDRIL_PLANT.get())
                .add(ModBlocks.FLOWERING_ENDER_TENDRIL.get());

        tag(BlockTags.CLIMBABLE)
            .addTag(ModTags.Blocks.ENDER_TENDRILS);
    }
}
