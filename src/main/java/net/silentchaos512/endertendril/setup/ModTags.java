package net.silentchaos512.endertendril.setup;

import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.silentchaos512.endertendril.EnderTendrilMod;

public class ModTags {
    public static class Blocks {
        public static final Tag.Named<Block> ENDER_TENDRILS = BlockTags.bind(EnderTendrilMod.getId("ender_tendrils").toString());
    }
}
