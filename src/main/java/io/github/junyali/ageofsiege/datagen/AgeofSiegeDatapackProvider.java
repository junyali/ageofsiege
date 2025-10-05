package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.worldgen.AgeofSiegeConfiguredFeatures;
import io.github.junyali.ageofsiege.worldgen.AgeofSiegePlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class AgeofSiegeDatapackProvider extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, AgeofSiegeConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, AgeofSiegePlacedFeatures::bootstrap);

	public AgeofSiegeDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(AgeofSiege.MODID));
	}
}
