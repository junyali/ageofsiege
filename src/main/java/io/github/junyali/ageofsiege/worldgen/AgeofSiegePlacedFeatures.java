package io.github.junyali.ageofsiege.worldgen;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class AgeofSiegePlacedFeatures {
	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
	}

	private static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AgeofSiege.MODID, name));
	}

	private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
	}
}
