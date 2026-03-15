package net.silentchaos512.endertendril.data.client;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
import net.silentchaos512.endertendril.setup.ModItems;

import java.util.function.BiConsumer;

public class ModItemModelProvider extends ItemModelGenerators {
    public ModItemModelProvider(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        this.generateFlatItem(ModItems.ENDER_TENDRIL_SEED.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TENDRIL_PEARL.get(), ModelTemplates.FLAT_ITEM);
    }
}
