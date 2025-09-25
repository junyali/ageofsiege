package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AgeofSiegeBlockLootTableProvider extends BlockLootSubProvider {
	protected AgeofSiegeBlockLootTableProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {
		// breaking block drops stuff idk
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks() {
		return AgeofSiegeBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
	}
}
