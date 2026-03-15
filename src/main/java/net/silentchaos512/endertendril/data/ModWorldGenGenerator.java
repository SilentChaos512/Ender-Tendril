package net.silentchaos512.endertendril.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModBlocks;
import net.silentchaos512.lib.data.worldgen.LibWorldGenProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenGenerator extends LibWorldGenProvider {
    public static final Identifier INVERTED_ENDER_TENDRIL = EnderTendrilMod.getId("inverted_ender_tendril");

    public ModWorldGenGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, EnderTendrilMod.MOD_ID);
    }

    @Override
    public void registerConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
        register(ctx, INVERTED_ENDER_TENDRIL, simpleBlockFeature(ModBlocks.INVERTED_ENDER_TENDRIL));
    }

    @Override
    public void registerPlacedFeatures(BootstrapContext<PlacedFeature> ctx) {
        register(ctx, INVERTED_ENDER_TENDRIL, configuredFeature -> placeOnSurfaceWithRarity(configuredFeature, 8));
    }

    @Override
    public void registerBiomeModifiers(BootstrapContext<BiomeModifier> ctx) {
        registerBiomeAddFeature(
                ctx,
                EnderTendrilMod.getId("the_end"),
                BiomeTags.IS_END,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                List.of(
                        INVERTED_ENDER_TENDRIL
                )
        );
    }
}
