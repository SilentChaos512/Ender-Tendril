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
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public EnderTendrilBlock(Properties builder) {
        super(builder, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected AbstractTopPlantBlock getHeadBlock() {
        return ModBlocks.ENDER_TENDRIL.get();
    }

    private Block getBodyPlant() {
        return ModBlocks.ENDER_TENDRIL_PLANT.get();
    }

    private Block getFloweringPlant() {
        return ModBlocks.FLOWERING_ENDER_TENDRIL.get();
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == this.growthDirection.getOpposite() && !stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
        }
        return stateIn;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos posUp = pos.above();
        BlockState stateUp = worldIn.getBlockState(posUp);
        BlockPos posDown = pos.below();
        BlockState stateDown = worldIn.getBlockState(posDown);

        return (stateUp.is(ModTags.Blocks.ENDER_TENDRILS) || stateUp.isFaceSturdy(worldIn, posUp, this.growthDirection))
                && stateDown.getBlock().is(ModTags.Blocks.ENDER_TENDRILS);
    }

    @Override
    public void destroy(IWorld worldIn, BlockPos pos, BlockState state) {
        BlockPos pos1 = pos.above();
        BlockState state1 = worldIn.getBlockState(pos1);

        while (state1.is(ModTags.Blocks.ENDER_TENDRILS)) {
            pos1 = pos1.above();
            state1 = worldIn.getBlockState(pos1);
        }

        worldIn.destroyBlock(pos1.below(), false);
    }
}
