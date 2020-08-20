package net.silentchaos512.endertendril.block;

import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

import java.util.Random;

public class EnderTendrilBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public EnderTendrilBlock(Properties builder) {
        super(builder, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return ModBlocks.ENDER_TENDRIL.get();
    }

    private Block getBodyPlant() {
        return ModBlocks.ENDER_TENDRIL_PLANT.get();
    }

    private Block getFloweringPlant() {
        return ModBlocks.FLOWERING_ENDER_TENDRIL.get();
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == this.growthDirection.getOpposite() && !stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }
        return stateIn;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos posUp = pos.up();
        BlockState stateUp = worldIn.getBlockState(posUp);
        BlockPos posDown = pos.down();
        BlockState stateDown = worldIn.getBlockState(posDown);

        return (stateUp.isIn(ModTags.Blocks.ENDER_TENDRILS) || stateUp.isSolidSide(worldIn, posUp, this.growthDirection))
                && stateDown.getBlock().isIn(ModTags.Blocks.ENDER_TENDRILS);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        BlockPos pos1 = pos.up();
        BlockState state1 = worldIn.getBlockState(pos1);

        while (state1.isIn(ModTags.Blocks.ENDER_TENDRILS)) {
            pos1 = pos1.up();
            state1 = worldIn.getBlockState(pos1);
        }

        worldIn.destroyBlock(pos1.down(), false);
    }
}
