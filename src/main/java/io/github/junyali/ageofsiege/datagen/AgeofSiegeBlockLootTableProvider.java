package io.github.junyali.ageofsiege.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

public class AgeofSiegeBlockLootTableProvider extends BlockLootSubProvider {
	protected AgeofSiegeBlockLootTableProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {
		// breaking block drops stuff idk
	}
}
