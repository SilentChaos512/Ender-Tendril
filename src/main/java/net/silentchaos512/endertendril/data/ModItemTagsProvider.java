package net.silentchaos512.endertendril.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.silentchaos512.endertendril.setup.ModItems;

public class ModItemTagsProvider extends ForgeItemTagsProvider {
    public ModItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(gen, blockTagProvider, existingFileHelper);
    }

    @Override
    public void registerTags() {
        getOrCreateBuilder(Tags.Items.ENDER_PEARLS)
                .add(ModItems.TENDRIL_PEARL.get());
    }
}
