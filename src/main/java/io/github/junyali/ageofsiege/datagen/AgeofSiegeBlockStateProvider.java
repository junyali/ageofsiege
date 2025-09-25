package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class AgeofSiegeBlockStateProvider extends BlockStateProvider {
	public AgeofSiegeBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, AgeofSiege.MODID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		// register blockstates hereeee
	}

	private void blockWithItem(DeferredBlock<?> deferredBlock) {
		simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
	}
}
