package net.silentchaos512.endertendril;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
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

    public EnderTendrilMod() {
        DistExecutor.safeRunForDist(() -> SideProxy.Client::new, () -> SideProxy.Server::new);

        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(DataGenerators::gatherData);
    }

    public static String getVersion() {
        return ModList.get().getModContainerById(MOD_ID)
                .map(c -> c.getModInfo().getVersion().toString())
                .orElse("INVALID"); // Should not happen
    }

    public static boolean isDevBuild() {
        return "NONE".equals(getVersion()) || !FMLLoader.isProduction();
    }


    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
