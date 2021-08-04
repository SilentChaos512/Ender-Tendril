package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(gen, blockTagProvider, EnderTendrilMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(Tags.Items.ENDER_PEARLS)
                .add(ModItems.TENDRIL_PEARL.get());
    }
}
