package net.silentchaos512.endertendril.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

import java.util.Random;

public class EnderTendrilBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public EnderTendrilBlock(Properties builder) {
        super(builder, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.ENDER_TENDRIL.get();
    }

    private Block getBodyPlant() {
        return ModBlocks.ENDER_TENDRIL_PLANT.get();
    }

    private Block getFloweringPlant() {
        return ModBlocks.FLOWERING_ENDER_TENDRIL.get();
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == this.growthDirection.getOpposite() && !stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
        }
        return stateIn;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos posUp = pos.above();
        BlockState stateUp = worldIn.getBlockState(posUp);
        BlockPos posDown = pos.below();
        BlockState stateDown = worldIn.getBlockState(posDown);

        return (stateUp.is(ModTags.Blocks.ENDER_TENDRILS) || stateUp.isFaceSturdy(worldIn, posUp, this.growthDirection))
                && stateDown.is(ModTags.Blocks.ENDER_TENDRILS);
    }

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        BlockPos pos1 = pos.above();
        BlockState state1 = worldIn.getBlockState(pos1);

        while (state1.is(ModTags.Blocks.ENDER_TENDRILS)) {
            pos1 = pos1.above();
            state1 = worldIn.getBlockState(pos1);
        }

        worldIn.destroyBlock(pos1.below(), false);
    }
}
