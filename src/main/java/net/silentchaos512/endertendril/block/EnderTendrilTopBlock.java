package net.silentchaos512.endertendril.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModTags;

import java.util.Random;

public class EnderTendrilTopBlock extends AbstractTopPlantBlock {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public EnderTendrilTopBlock(Properties properties) {
        // TODO: reduce growth chance
        super(properties, Direction.DOWN, SHAPE, false, 0.1);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected int getGrowthAmount(Random rand) {
        return 0;
    }

    @Override
    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Override
    protected Block func_230330_d_() {
        return ModBlocks.ENDER_TENDRIL_PLANT.get();
    }

    private Block getFloweringPlant() {
        return ModBlocks.FLOWERING_ENDER_TENDRIL.get();
    }

    private BlockState getGrownBlock(IBlockReader worldIn, BlockPos currentPos) {
        for (int i = 1; i < 4; ++i) {
            BlockState state = worldIn.getBlockState(currentPos.up(i));
            if (state.getBlock() != ModBlocks.ENDER_TENDRIL_PLANT.get()) {
                return func_230330_d_().getDefaultState();
            }
        }
        return getFloweringPlant().getDefaultState();
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == this.growthDirection.getOpposite() && !stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        if (facing == this.growthDirection && facingState.isIn(this)) {
            return this.getGrownBlock(worldIn, currentPos);
        } else {
            if (this.waterloggable) {
                worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            }

            return stateIn;
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.offset(this.growthDirection.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();

        return block.isIn(ModTags.Blocks.ENDER_TENDRILS) || blockstate.isSolidSide(worldIn, blockpos, this.growthDirection);
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
