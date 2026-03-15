package net.silentchaos512.endertendril.setup;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.item.EnderTendrilSeedItem;

@EventBusSubscriber
public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EnderTendrilMod.MOD_ID);

    public static final DeferredItem<EnderTendrilSeedItem> ENDER_TENDRIL_SEED = ITEMS.registerItem(
            "ender_tendril_seed",
            properties -> new EnderTendrilSeedItem(ModBlocks.ENDER_TENDRIL.get(), properties),
            Item.Properties::useItemDescriptionPrefix
    );
    public static final DeferredItem<Item> TENDRIL_PEARL = ITEMS.registerSimpleItem("tendril_pearl");

    private ModItems() {}

    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ENDER_TENDRIL_SEED);
            event.accept(TENDRIL_PEARL);
        }
    }
}
