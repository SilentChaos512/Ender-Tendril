package net.silentchaos512.endertendril.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.silentchaos512.endertendril.EnderTendrilMod;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.Objects;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, EnderTendrilMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(new ResourceLocation("item/generated"));

        simpleBuilder(ModItems.ENDER_TENDRIL_SEED, itemGenerated);
        simpleBuilder(ModItems.TENDRIL_PEARL, itemGenerated);
    }

    private void simpleBuilder(Supplier<? extends Item> item, ModelFile parent) {
        String path = Objects.requireNonNull(item.get().getRegistryName()).getPath();
        simpleBuilder(item, parent, "item/" + path);
    }

    private void simpleBuilder(Supplier<? extends Item> item, ModelFile parent, String texture) {
        String path = Objects.requireNonNull(item.get().getRegistryName()).getPath();
        getBuilder(path).parent(parent).texture("layer0", texture);
    }
}
