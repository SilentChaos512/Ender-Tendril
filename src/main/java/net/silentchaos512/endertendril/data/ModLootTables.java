package net.silentchaos512.endertendril.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.silentchaos512.endertendril.block.FloweringEnderTendrilBlock;
import net.silentchaos512.endertendril.setup.LootInjector;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.endertendril.setup.ModItems;
import net.silentchaos512.endertendril.setup.Registration;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTables extends LootTableProvider {
    public ModLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(Blocks::new, LootParameterSets.BLOCK),
                Pair.of(Chests::new, LootParameterSets.CHEST)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((name, lootTable) -> LootTableManager.func_227508_a_(validationtracker, name, lootTable));
    }

    private static final class Blocks extends BlockLootTables {
        @Override
        protected void addTables() {
            registerDropping(ModBlocks.ENDER_TENDRIL.get(), ModItems.ENDER_TENDRIL_SEED.get());
            registerLootTable(ModBlocks.ENDER_TENDRIL_PLANT.get(), LootTable.builder());

            BlockStateProperty.Builder floweringTendrilMature = BlockStateProperty.builder(ModBlocks.FLOWERING_ENDER_TENDRIL.get())
                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                            .withIntProp(FloweringEnderTendrilBlock.AGE, ModBlocks.FLOWERING_ENDER_TENDRIL.get().getMaxAge())
                    );
            registerLootTable(ModBlocks.FLOWERING_ENDER_TENDRIL.get(), LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .acceptCondition(floweringTendrilMature)
                            .addEntry(ItemLootEntry.builder(ModItems.TENDRIL_PEARL.get()))
                    )
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .acceptCondition(floweringTendrilMature)
                            .addEntry(ItemLootEntry.builder(ModItems.ENDER_TENDRIL_SEED.get())
                                    .weight(1)
                                    .acceptCondition(RandomChance.builder(0.1f))
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

    private static final class Chests extends ChestLootTables {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            consumer.accept(LootInjector.Tables.CHESTS_ABANDONED_MINESHAFT, addSeeds(7));
            consumer.accept(LootInjector.Tables.CHESTS_BURIED_TREASURE, addSeeds(4));
            consumer.accept(LootInjector.Tables.CHESTS_END_CITY_TREASURE, addSeeds(2));
            consumer.accept(LootInjector.Tables.CHESTS_SHIPWRECK_TREASURE, addSeeds(5));
            consumer.accept(LootInjector.Tables.CHESTS_STRONGHOLD_CORRIDOR, addSeeds(2));
            consumer.accept(LootInjector.Tables.CHESTS_STRONGHOLD_CROSSING, addSeeds(3));
        }

        private static LootTable.Builder addSeeds(int emptyWeight) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .bonusRolls(0, 1)
                            .addEntry(EmptyLootEntry.func_216167_a()
                                    .weight(emptyWeight)
                            )
                            .addEntry(ItemLootEntry.builder(ModItems.ENDER_TENDRIL_SEED.get())
                                    .weight(1)
                            )
                    );
        }
    }
}
