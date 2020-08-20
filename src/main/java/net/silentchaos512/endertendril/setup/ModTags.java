package net.silentchaos512.endertendril.setup;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.silentchaos512.endertendril.EnderTendrilMod;

public class ModTags {
    public static class Blocks {
        public static final ITag.INamedTag<Block> ENDER_TENDRILS = BlockTags.makeWrapperTag(EnderTendrilMod.getId("ender_tendrils").toString());
    }
}
