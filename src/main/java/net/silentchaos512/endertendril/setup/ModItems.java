package net.silentchaos512.endertendril.setup;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.endertendril.item.EnderTendrilSeedItem;

import java.util.function.Supplier;

public final class ModItems {
    public static final RegistryObject<EnderTendrilSeedItem> ENDER_TENDRIL_SEED = register("ender_tendril_seed", () ->
            new EnderTendrilSeedItem(ModBlocks.ENDER_TENDRIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> TENDRIL_PEARL = register("tendril_pearl", () ->
            new Item(new Item.Properties()));

    private ModItems() {}

    static void register() {}

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}
