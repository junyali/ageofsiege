package io.github.junyali.ageofsiege.worldgen;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AgeofSiegeBiomeModifiers {
	public static final ResourceKey<BiomeModifier> ADD_SALT_BLOCK_BEACH = registerKey("add_salt_block_beach");
	public static final ResourceKey<BiomeModifier> ADD_SALT_BLOCK_OCEAN = registerKey("add_salt_block_ocean");
	public static final ResourceKey<BiomeModifier> ADD_SALT_BLOCK_RIVER = registerKey("add_salt_block_river");

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var biomes = context.lookup(Registries.BIOME);

		context.register(ADD_SALT_BLOCK_BEACH, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomes.getOrThrow(BiomeTags.IS_BEACH),
				HolderSet.direct(placedFeatures.getOrThrow(AgeofSiegePlacedFeatures.SALT_BLOCK_PLACED_KEY)),
				GenerationStep.Decoration.UNDERGROUND_ORES
		));

		context.register(ADD_SALT_BLOCK_OCEAN, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomes.getOrThrow(BiomeTags.IS_OCEAN),
				HolderSet.direct(placedFeatures.getOrThrow(AgeofSiegePlacedFeatures.SALT_BLOCK_PLACED_KEY)),
				GenerationStep.Decoration.UNDERGROUND_ORES
		));

		context.register(ADD_SALT_BLOCK_RIVER, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomes.getOrThrow(BiomeTags.IS_RIVER),
				HolderSet.direct(placedFeatures.getOrThrow(AgeofSiegePlacedFeatures.SALT_BLOCK_PLACED_KEY)),
				GenerationStep.Decoration.UNDERGROUND_ORES
		));
	}

	private static ResourceKey<BiomeModifier> registerKey(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AgeofSiege.MODID, name));
	}
}
