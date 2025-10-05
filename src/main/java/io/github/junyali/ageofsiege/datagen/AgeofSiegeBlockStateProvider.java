package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import io.github.junyali.ageofsiege.block.custom.CoinPileBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class AgeofSiegeBlockStateProvider extends BlockStateProvider {
	public AgeofSiegeBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, AgeofSiege.MODID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		// register blockstates hereeee
		blockWithItem(AgeofSiegeBlocks.SALT_BLOCK);
		simpleBlockWithItem(AgeofSiegeBlocks.CRATE_BLOCK.get(),
				models().getExistingFile(modLoc("block/crate")));
		getVariantBuilder(AgeofSiegeBlocks.COIN_PILE_BLOCK.get())
				.forAllStates(blockState -> {
					int layers = blockState.getValue(CoinPileBlock.LAYERS);
					int height = layers * 2;

					return ConfiguredModel.builder()
							.modelFile(models().withExistingParent("coin_pile_height" + height, "block/block")
									.texture("particle", modLoc("block/coin_pile"))
									.texture("texture", modLoc("block/coin_pile"))
									.element()
									.from(0, 0, 0)
									.to(16, height, 16)
									.allFaces((direction, faceBuilder) -> {
										faceBuilder.texture("#texture");
										if (direction == Direction.DOWN) {
											faceBuilder.cullface(direction);
										}
									})
									.end())
							.build();
				});
		itemModels().withExistingParent("coin_pile", modLoc("block/coin_pile_height4"));
	}

	private void blockWithItem(DeferredBlock<?> deferredBlock) {
		simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
	}
}
