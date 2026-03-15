package net.silentchaos512.endertendril.data.client;

import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.lib.data.client.LibBlockModelGenerators;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerator extends LibBlockModelGenerators {
    public ModBlockModelGenerator(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        createCrossBlock(ModBlocks.ENDER_TENDRIL.get(), PlantType.NOT_TINTED);
        createCrossBlock(ModBlocks.ENDER_TENDRIL_PLANT.get(), PlantType.NOT_TINTED);
        createFloweringTendril(ModBlocks.FLOWERING_ENDER_TENDRIL.get());
        createCrossBlock(ModBlocks.INVERTED_ENDER_TENDRIL.get(), PlantType.NOT_TINTED);
    }

    private void createFloweringTendril(FloweringEnderTendrilBlock block) {
        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block)
                        .with(
                                PropertyDispatch.initial(FloweringEnderTendrilBlock.AGE)
                                        .generate(age -> {
                                            Identifier texture = TextureMapping.getBlockTexture(
                                                    block,
                                                    "_" + getFlowerStage(age)
                                            );
                                            Identifier model = ModelTemplates.CROSS.createWithSuffix(
                                                    block,
                                                    "_" + age,
                                                    new TextureMapping().put(TextureSlot.CROSS, texture),
                                                    this.modelOutput
                                            );
                                            return plainVariant(model);
                                        })
                        )
        );
    }

    private static int getFlowerStage(int age) {
        if (age == 15) return 4;
        if (age > 12) return 3;
        if (age > 8) return 2;
        if (age > 4) return 1;
        return 0;
    }
}
