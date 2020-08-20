package net.silentchaos512.endertendril.setup;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public final class ModItems {
    public static final RegistryObject<BlockNamedItem> ENDER_TENDRIL_SEED = register("ender_tendril_seed", () ->
            new BlockNamedItem(ModBlocks.ENDER_TENDRIL.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> TENDRIL_PEARL = register("tendril_pearl", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));

    private ModItems() {}

    static void register() {}

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> itemSupplier) {
        return Registration.ITEMS.register(name, itemSupplier);
    }
}
