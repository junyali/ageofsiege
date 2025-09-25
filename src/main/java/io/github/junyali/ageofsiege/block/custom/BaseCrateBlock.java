package io.github.junyali.ageofsiege.block.custom;

import com.mojang.serialization.MapCodec;
import io.github.junyali.ageofsiege.block.entity.BaseCrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
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
	public BaseCrateBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(OPEN);
	}

	@Override
	protected @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos blockPos,
	                                         @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}

		BlockEntity blockEntity = level.getBlockEntity(blockPos);
		if (blockEntity instanceof BaseCrateBlockEntity crateEntity) {
			player.openMenu((MenuProvider) crateEntity);
		}
		return InteractionResult.SUCCESS;
	}
}
