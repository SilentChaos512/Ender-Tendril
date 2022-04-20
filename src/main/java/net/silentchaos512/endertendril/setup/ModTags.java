package net.silentchaos512.endertendril.setup;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.silentchaos512.endertendril.EnderTendrilMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> ENDER_TENDRILS = BlockTags.create(EnderTendrilMod.getId("ender_tendrils"));
    }
}
