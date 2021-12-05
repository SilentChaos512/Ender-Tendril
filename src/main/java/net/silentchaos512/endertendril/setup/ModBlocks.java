package net.silentchaos512.endertendril.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.endertendril.block.EnderTendrilBlock;
import net.silentchaos512.endertendril.block.EnderTendrilTopBlock;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;

import java.util.function.Supplier;

public final class ModBlocks {
    public static final RegistryObject<EnderTendrilTopBlock> ENDER_TENDRIL = register("ender_tendril", () ->
            new EnderTendrilTopBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .randomTicks()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
            ));
    public static final RegistryObject<EnderTendrilBlock> ENDER_TENDRIL_PLANT = register("ender_tendril_plant", () ->
            new EnderTendrilBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
            ));
    public static final RegistryObject<FloweringEnderTendrilBlock> FLOWERING_ENDER_TENDRIL = register("flowering_ender_tendril", () ->
            new FloweringEnderTendrilBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .randomTicks()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
            ));

    private ModBlocks() {}

    public static void register() {}

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ENDER_TENDRIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ENDER_TENDRIL_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLOWERING_ENDER_TENDRIL.get(), RenderType.cutout());
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return Registration.BLOCKS.register(name, blockSupplier);
    }
}
