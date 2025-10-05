package io.github.junyali.ageofsiege.block.custom;

import com.mojang.serialization.MapCodec;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoinPileBlock extends Block {
	public static final MapCodec<CoinPileBlock> CODEC = simpleCodec(CoinPileBlock::new);
	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;

	private static final VoxelShape[] SHAPES = new VoxelShape[]{
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
			Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
	};

	public CoinPileBlock(Properties properties) {
		// basically a snow layer block but on steroids
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1));
	}

	@Override
	protected @NotNull MapCodec<? extends Block> codec() { return CODEC; }

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LAYERS);
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos, @NotNull CollisionContext ctx) {
		return SHAPES[blockState.getValue(LAYERS) - 1];
	}

	@Override
	public @NotNull List<ItemStack> getDrops(BlockState blockState, LootParams.@NotNull Builder builder) {
		return List.of(new ItemStack(AgeofSiegeItems.COIN.get(), blockState.getValue(LAYERS)));
	}

	@Override
	protected boolean canBeReplaced(BlockState blockState, BlockPlaceContext ctx) {
		int layers = blockState.getValue(LAYERS);
		if (ctx.getItemInHand().is(this.asItem()) && layers < 8) {
			return !ctx.replacingClickedOnBlock() || ctx.getClickedFace() == Direction.UP;
		}
		return layers == 1;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState existingState = ctx.getLevel().getBlockState(ctx.getClickedPos());
		if (existingState.is(this)) {
			int layers = existingState.getValue(LAYERS);
			return existingState.setValue(LAYERS, Math.min(8, layers + 1));
		}
		return this.defaultBlockState();
	}
}
