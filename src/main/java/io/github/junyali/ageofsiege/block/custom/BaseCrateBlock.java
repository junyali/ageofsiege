package io.github.junyali.ageofsiege.block.custom;

import io.github.junyali.ageofsiege.block.entity.BaseCrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public abstract class BaseCrateBlock extends BaseEntityBlock {
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	public abstract int getInventorySize();

	public BaseCrateBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(OPEN);
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
		if (blockEntity instanceof BaseCrateBlockEntity crateEntity) {
			player.openMenu(crateEntity);
			return InteractionResult.CONSUME;
		}
		return InteractionResult.SUCCESS;
	}
}
