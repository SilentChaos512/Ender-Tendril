package net.silentchaos512.endertendril.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class FloweringEnderTendrilBlock extends EnderTendrilBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;

    public FloweringEnderTendrilBlock(Properties builder) {
        super(builder);
        registerDefaultState(defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public int getAge(BlockState state) {
        return state.getValue(AGE);
    }

    public int getMaxAge() {
        return 15;
    }

    public boolean isMaxAge(BlockState state) {
        return getAge(state) >= getMaxAge();
    }

    public BlockState withAge(int age) {
        return this.defaultBlockState().setValue(AGE, age);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !isMaxAge(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isAreaLoaded(pos, 1)) return;

        int i = this.getAge(state);
        if (i < this.getMaxAge()) {
            float f = 1f;
            if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                worldIn.setBlock(pos, this.withAge(i + 1), 2);
                ForgeHooks.onCropsGrowPost(worldIn, pos, state);
            }
        }
    }
}
