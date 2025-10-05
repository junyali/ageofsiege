package io.github.junyali.ageofsiege.worldgen;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class AgeofSiegePlacedFeatures {
	public static final ResourceKey<PlacedFeature> SALT_BLOCK_PLACED_KEY = registerKey("salt_block_placed");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		register(context, SALT_BLOCK_PLACED_KEY, configuredFeatures.getOrThrow(AgeofSiegeConfiguredFeatures.SALT_BLOCK_KEY),
				List.of(
						CountPlacement.of(4),
						InSquarePlacement.spread(),
						HeightRangePlacement.uniform(
								VerticalAnchor.absolute(50),
								VerticalAnchor.absolute(70)
						),
						BiomeFilter.biome()
				));
	}

	private static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AgeofSiege.MODID, name));
	}

	private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
	}
}
