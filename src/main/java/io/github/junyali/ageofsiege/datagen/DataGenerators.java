package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = AgeofSiege.MODID)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		// SERVER
		generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(AgeofSiegeBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
		generator.addProvider(event.includeServer(), new AgeofSiegeRecipeProvider(packOutput, lookupProvider));

		BlockTagsProvider blockTagsProvider = new AgeofSiegeBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTagsProvider);
		generator.addProvider(event.includeServer(), new AgeofSiegeItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
		generator.addProvider(event.includeServer(), new AgeofSiegeDataMapProvider(packOutput, lookupProvider));
		generator.addProvider(event.includeServer(), new AgeofSiegePOITagsProvider(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(event.includeServer(), new AgeofSiegeDatapackProvider(packOutput, lookupProvider));

		// CLIENT
		generator.addProvider(event.includeClient(), new AgeofSiegeItemModelProvider(packOutput, existingFileHelper));
		generator.addProvider(event.includeClient(), new AgeofSiegeBlockStateProvider(packOutput, existingFileHelper));

		// may also add datagen for lang if needed
	}
}
