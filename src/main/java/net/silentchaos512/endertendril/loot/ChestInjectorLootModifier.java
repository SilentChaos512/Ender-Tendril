package net.silentchaos512.endertendril.loot;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.silentchaos512.endertendril.setup.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public class ChestInjectorLootModifier extends LootModifier {
    private static final Map<ResourceLocation, Float> SEED_CHANCE = ImmutableMap.<ResourceLocation, Float>builder()
            .put(BuiltInLootTables.ABANDONED_MINESHAFT, 1f / 3f)
            .put(BuiltInLootTables.BASTION_TREASURE, 2f / 3f)
            .put(BuiltInLootTables.BURIED_TREASURE, 1f / 3f)
            .put(BuiltInLootTables.END_CITY_TREASURE, 2f / 3f)
            .put(BuiltInLootTables.JUNGLE_TEMPLE, 1f / 3f)
            .put(BuiltInLootTables.SHIPWRECK_TREASURE, 1f / 3f)
            .put(BuiltInLootTables.STRONGHOLD_CORRIDOR, 2f / 3f)
            .put(BuiltInLootTables.STRONGHOLD_CROSSING, 3f / 5f)
            .build();

    public static final Supplier<Codec<ChestInjectorLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst ->
                    codecStart(inst).apply(inst, ChestInjectorLootModifier::new)
            )
    );

    public ChestInjectorLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> ret = new ObjectArrayList<>(generatedLoot);
        ResourceLocation baseTableId = context.getQueriedLootTableId();
        float seedChance = SEED_CHANCE.getOrDefault(baseTableId, 0.0f);
        if (context.getRandom().nextFloat() < seedChance) {
            ret.add(new ItemStack(ModItems.ENDER_TENDRIL_SEED.get()));
        }
        return ret;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
