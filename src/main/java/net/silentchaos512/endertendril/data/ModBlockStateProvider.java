package net.silentchaos512.endertendril.data;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;
import net.silentchaos512.endertendril.setup.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), EnderTendrilMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(ModBlocks.ENDER_TENDRIL.get()).forAllStates(state -> {
            return ConfiguredModel.builder()
                    .modelFile(models()
                            .cross("ender_tendril", modLoc("block/ender_tendril"))
                            .renderType("cutout")
                    )
                    .build();
        });
        getVariantBuilder(ModBlocks.ENDER_TENDRIL_PLANT.get()).forAllStates(state -> {
            return ConfiguredModel.builder()
                    .modelFile(models()
                            .cross("ender_tendril_plant", modLoc("block/ender_tendril_plant"))
                            .renderType("cutout")
                    )
                    .build();
        });
        getVariantBuilder(ModBlocks.FLOWERING_ENDER_TENDRIL.get()).forAllStates(state -> {
            int stage = getFlowerStage(state);
            return ConfiguredModel.builder()
                    .modelFile(models()
                            .cross("flowering_ender_tendril_" + stage, modLoc("block/flowering_ender_tendril_" + stage))
                            .renderType("cutout")
                    )
                    .build();
        });
    }

    private static int getFlowerStage(BlockState state) {
        int age = state.getValue(FloweringEnderTendrilBlock.AGE);
        if (age == 15) return 4;
        if (age > 12) return 3;
        if (age > 8) return 2;
        if (age > 4) return 1;
        return 0;
    }
}
