package io.github.junyali.ageofsiege.block.custom;

import com.mojang.serialization.MapCodec;
import io.github.junyali.ageofsiege.block.entity.CrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrateBlock extends BaseEntityBlock {
	public static final MapCodec<CrateBlock> CODEC = simpleCodec(CrateBlock::new);
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

	public CrateBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
	}

	@Override
	protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(OPEN);
	}

	@Override
	protected @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new CrateBlockEntity(blockPos, blockState);
	}

	@Override
	protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull BlockHitResult hit) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}

		BlockEntity blockEntity = level.getBlockEntity(blockPos);
		if (blockEntity instanceof CrateBlockEntity crateEntity) {
			player.openMenu(crateEntity);
			return InteractionResult.CONSUME;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void onRemove(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState newBlockState, boolean isMoved) {
		if (!blockState.is(newBlockState.getBlock())) {
			BlockEntity blockEntity = level.getBlockEntity(blockPos);
			if (blockEntity instanceof CrateBlockEntity crateEntity) {
				level.updateNeighbourForOutputSignal(blockPos, this);
			}
		}
		super.onRemove(blockState, level, blockPos, newBlockState, isMoved);
	}

	@Override
	protected @NotNull List<ItemStack> getDrops(@NotNull BlockState blockState, LootParams.@NotNull Builder lootParams) {
		List<ItemStack> drops = super.getDrops(blockState, lootParams);

		BlockEntity blockEntity = lootParams.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (blockEntity instanceof CrateBlockEntity crateEntity) {
			ItemStack crateItem = new ItemStack(this);

			CompoundTag nbt = new CompoundTag();
			// store inventory code goes here l8r when i refactor the entity class
		}

		return drops;
	}
}
