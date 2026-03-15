package net.silentchaos512.endertendril;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.silentchaos512.endertendril.client.EnderTendrilClient;
import net.silentchaos512.endertendril.data.DataGenerators;
import net.silentchaos512.endertendril.setup.Registration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(EnderTendrilMod.MOD_ID)
public final class EnderTendrilMod {
    public static final String MOD_ID = "endertendril";
    public static final String MOD_NAME = "Ender Tendril";

    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final Random RANDOM = new Random();
    public static final RandomSource RANDOM_SOURCE = RandomSource.create();

    public EnderTendrilMod(IEventBus modEventBus, ModContainer modContainer) {
        Registration.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);
        modContainer.registerConfig(ModConfig.Type.COMMON, EnderTendrilConfig.COMMON_SPEC);

        if (FMLEnvironment.getDist() == Dist.CLIENT) {
            new EnderTendrilClient(modEventBus, modContainer);
        }
    }

    public static Identifier getId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
