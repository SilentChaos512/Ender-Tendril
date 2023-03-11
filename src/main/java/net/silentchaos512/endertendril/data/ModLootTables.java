package net.silentchaos512.endertendril.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaChestLoot;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;
import net.silentchaos512.endertendril.setup.LootInjector;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModItems;
import net.silentchaos512.endertendril.setup.Registration;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ModLootTables extends LootTableProvider {
    public ModLootTables(PackOutput packOutput) {
        super(packOutput, Collections.emptySet(), VanillaLootTableProvider.create(packOutput).getTables());
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return ImmutableList.of(
                new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(Chests::new, LootContextParamSets.CHEST)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((name, lootTable) -> LootTables.validate(validationtracker, name, lootTable));
    }

    private static final class Blocks extends BlockLootSubProvider {
        public Blocks() {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
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
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }

    private static final class Chests extends VanillaChestLoot {
        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            consumer.accept(LootInjector.Tables.CHESTS_ABANDONED_MINESHAFT, addSeeds(1, 2));
            consumer.accept(LootInjector.Tables.CHESTS_BURIED_TREASURE, addSeeds(1, 2));
            consumer.accept(LootInjector.Tables.CHESTS_END_CITY_TREASURE, addSeeds(2, 1));
            consumer.accept(LootInjector.Tables.CHESTS_SHIPWRECK_TREASURE, addSeeds(1, 2));
            consumer.accept(LootInjector.Tables.CHESTS_STRONGHOLD_CORRIDOR, addSeeds(2, 1));
            consumer.accept(LootInjector.Tables.CHESTS_STRONGHOLD_CROSSING, addSeeds(3, 2));
        }

        private static LootTable.Builder addSeeds(int seedWeight, int emptyWeight) {
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .setBonusRolls(UniformGenerator.between(0, 1))
                            .add(EmptyLootItem.emptyItem()
                                    .setWeight(emptyWeight)
                            )
                            .add(LootItem.lootTableItem(ModItems.ENDER_TENDRIL_SEED.get())
                                    .setWeight(seedWeight)
                            )
                    );
        }
    }
}
