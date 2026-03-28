package net.silentchaos512.endertendril.data;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLootTables extends LootTableProvider {
    public ModLootTables(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
                packOutput,
                Collections.emptySet(),
                VanillaLootTableProvider.create(packOutput, lookupProvider).getTables(),
                lookupProvider
        );
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return List.of(
                new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(WritableRegistry<LootTable> tables, ValidationContextSource validationContext, ProblemReporter.Collector problems) {
        // Don't validate against built-in tables
    }

    private static final class Blocks extends BlockLootSubProvider {
        public Blocks(HolderLookup.Provider lookupProvider) {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
        }

        @Override
        protected void generate() {
            dropOther(ModBlocks.INVERTED_ENDER_TENDRIL.get(), ModItems.ENDER_TENDRIL_SEED.get());
            dropOther(ModBlocks.ENDER_TENDRIL.get(), ModItems.ENDER_TENDRIL_SEED.get());
            add(ModBlocks.ENDER_TENDRIL_PLANT.get(), LootTable.lootTable());

            LootItemBlockStatePropertyCondition.Builder floweringTendrilMature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.FLOWERING_ENDER_TENDRIL.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(FloweringEnderTendrilBlock.AGE, ModBlocks.FLOWERING_ENDER_TENDRIL.get().getMaxAge())
                    );
            add(ModBlocks.FLOWERING_ENDER_TENDRIL.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(floweringTendrilMature)
                            .add(LootItem.lootTableItem(ModItems.TENDRIL_PEARL.get()))
                    )
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(floweringTendrilMature)
                            .add(LootItem.lootTableItem(ModItems.ENDER_TENDRIL_SEED.get())
                                    .setWeight(1)
                                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                            )
                    )
            );
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream()
                    .map(DeferredHolder::get)
                    .collect(Collectors.toList());
        }
    }
}
