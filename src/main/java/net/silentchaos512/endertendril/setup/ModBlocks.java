package net.silentchaos512.endertendril.setup;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.endertendril.block.EnderTendrilBlock;
import net.silentchaos512.endertendril.block.EnderTendrilTopBlock;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;

import java.util.function.Supplier;

public final class ModBlocks {
    public static final RegistryObject<EnderTendrilTopBlock> ENDER_TENDRIL = register("ender_tendril", () ->
            new EnderTendrilTopBlock(BlockBehaviour.Properties.of()
                    .randomTicks()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
            ));
    public static final RegistryObject<EnderTendrilBlock> ENDER_TENDRIL_PLANT = register("ender_tendril_plant", () ->
            new EnderTendrilBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
            ));
    public static final RegistryObject<FloweringEnderTendrilBlock> FLOWERING_ENDER_TENDRIL = register("flowering_ender_tendril", () ->
            new FloweringEnderTendrilBlock(BlockBehaviour.Properties.of()
                    .randomTicks()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
            ));

    private ModBlocks() {}

    public static void register() {}

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return Registration.BLOCKS.register(name, blockSupplier);
    }
}
