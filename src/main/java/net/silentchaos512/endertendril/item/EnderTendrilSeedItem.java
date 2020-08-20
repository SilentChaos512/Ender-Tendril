package net.silentchaos512.endertendril.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class EnderTendrilSeedItem extends BlockNamedItem {
    public EnderTendrilSeedItem(Block blockIn, Properties properties) {
        super(blockIn, properties);
    }

    @Override
    protected boolean canPlace(BlockItemUseContext context, BlockState state) {
        BlockPos posUp = context.getPos().up();
        BlockState stateUp = context.getWorld().getBlockState(posUp);
        return stateUp.isSolidSide(context.getWorld(), posUp, Direction.DOWN) && super.canPlace(context, state);
    }
}
