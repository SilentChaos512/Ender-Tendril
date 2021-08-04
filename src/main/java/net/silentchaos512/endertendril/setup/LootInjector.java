package net.silentchaos512.endertendril.setup;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.silentchaos512.endertendril.EnderTendrilMod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = EnderTendrilMod.MOD_ID)
public final class LootInjector {
    public static final class Tables {
        private static final Map<ResourceLocation, ResourceLocation> MAP = new HashMap<>();

        public static final ResourceLocation CHESTS_ABANDONED_MINESHAFT = inject(BuiltInLootTables.ABANDONED_MINESHAFT);
        public static final ResourceLocation CHESTS_BURIED_TREASURE = inject(BuiltInLootTables.BURIED_TREASURE);
        public static final ResourceLocation CHESTS_END_CITY_TREASURE = inject(BuiltInLootTables.END_CITY_TREASURE);
        public static final ResourceLocation CHESTS_SHIPWRECK_TREASURE = inject(BuiltInLootTables.SHIPWRECK_TREASURE);
        public static final ResourceLocation CHESTS_STRONGHOLD_CORRIDOR = inject(BuiltInLootTables.STRONGHOLD_CORRIDOR);
        public static final ResourceLocation CHESTS_STRONGHOLD_CROSSING = inject(BuiltInLootTables.STRONGHOLD_CROSSING);

        private Tables() {}

        public static Collection<ResourceLocation> getValues() {
            return MAP.values();
        }

        public static Optional<ResourceLocation> get(ResourceLocation lootTable) {
            return Optional.ofNullable(MAP.get(lootTable));
        }

        private static ResourceLocation inject(ResourceLocation lootTable) {
            ResourceLocation ret = EnderTendrilMod.getId("inject/" + lootTable.getPath());
            MAP.put(lootTable, ret);
            return ret;
        }
    }

    private LootInjector() {}

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        Tables.get(event.getName()).ifPresent(injectorName -> {
            EnderTendrilMod.LOGGER.info("Injecting loot table '{}' into '{}'", injectorName, event.getName());
            event.getTable().addPool(
                    LootPool.lootPool()
                            .name("endertendril_injected")
                            .add(LootTableReference.lootTableReference(injectorName))
                            .build()
            );
        });
    }
}
