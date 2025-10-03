package io.github.junyali.ageofsiege.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;

public class CoinPileBlock extends Block {
	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;

	public CoinPileBlock() {
		// basically a snow layer block but on steroids
		super(BlockBehaviour.Properties.of()
				.mapColor(MapColor.GOLD)
				.strength(0.2F)
				.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1));
	}
}
