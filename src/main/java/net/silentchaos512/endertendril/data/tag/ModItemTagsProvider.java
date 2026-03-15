package net.silentchaos512.endertendril.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

public class ModItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {
    public ModItemTagsProvider(GatherDataEvent event) {
        //noinspection deprecation
        super(event.getGenerator().getPackOutput(), Registries.ITEM, event.getLookupProvider(), item -> item.builtInRegistryHolder().key(), EnderTendrilMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.ENDER_PEARLS)
                .add(ModItems.TENDRIL_PEARL.get());
    }
}
