package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AgeofSiegeBlockTagProvider extends BlockTagsProvider {
	public AgeofSiegeBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AgeofSiege.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		// tags go here!
		tag(BlockTags.MINEABLE_WITH_SHOVEL)
				.add(AgeofSiegeBlocks.SALT_BLOCK.get());
		tag(BlockTags.MINEABLE_WITH_AXE)
				.add(AgeofSiegeBlocks.CRATE_BLOCK.get());
	}
}
