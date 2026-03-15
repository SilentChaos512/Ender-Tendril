package net.silentchaos512.endertendril.setup;

import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.block.EnderTendrilBlock;
import net.silentchaos512.endertendril.block.EnderTendrilTopBlock;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;
import net.silentchaos512.endertendril.block.InvertedEnderTendrilBlock;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EnderTendrilMod.MOD_ID);

    public static final DeferredBlock<EnderTendrilTopBlock> ENDER_TENDRIL = BLOCKS.registerBlock(
            "ender_tendril",
            EnderTendrilTopBlock::new,
            properties -> properties
                    .randomTicks()
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );
    public static final DeferredBlock<EnderTendrilBlock> ENDER_TENDRIL_PLANT = BLOCKS.registerBlock(
            "ender_tendril_plant",
            EnderTendrilBlock::new,
            properties -> properties
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );
    public static final DeferredBlock<FloweringEnderTendrilBlock> FLOWERING_ENDER_TENDRIL = BLOCKS.registerBlock(
            "flowering_ender_tendril",
            FloweringEnderTendrilBlock::new,
            properties -> properties
                    .randomTicks()
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );
    public static final DeferredBlock<InvertedEnderTendrilBlock> INVERTED_ENDER_TENDRIL = BLOCKS.registerBlock(
            "inverted_ender_tendril",
            InvertedEnderTendrilBlock::new,
            properties -> properties
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
    );

    private ModBlocks() {
    }
}
