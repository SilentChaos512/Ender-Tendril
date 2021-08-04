package net.silentchaos512.endertendril.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EnderTendrilSeedItem extends ItemNameBlockItem {
    public EnderTendrilSeedItem(Block blockIn, Properties properties) {
        super(blockIn, properties);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
        BlockPos posUp = context.getClickedPos().above();
        BlockState stateUp = context.getLevel().getBlockState(posUp);
        return stateUp.isFaceSturdy(context.getLevel(), posUp, Direction.DOWN) && super.canPlace(context, state);
    }
}
