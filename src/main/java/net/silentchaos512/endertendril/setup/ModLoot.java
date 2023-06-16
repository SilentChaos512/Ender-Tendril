package net.silentchaos512.endertendril.setup;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.loot.ChestInjectorLootModifier;

public final class ModLoot {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EnderTendrilMod.MOD_ID);

    public static final RegistryObject<Codec<ChestInjectorLootModifier>> CHEST_LOOT_INJECTOR =
            LOOT_MODIFIERS.register("chest_loot_injector", ChestInjectorLootModifier.CODEC);

    private ModLoot() {}
}
