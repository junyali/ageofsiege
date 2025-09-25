package io.github.junyali.ageofsiege.block.custom;

import com.mojang.serialization.MapCodec;
import io.github.junyali.ageofsiege.block.entity.BaseCrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public abstract class BaseCrateBlock extends BaseEntityBlock {
	public BaseCrateBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	public abstract int getInventorySize();

	@Override
	protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	protected @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	protected @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos,
	                                         @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
		if (!level.isClientSide) {
			if (level.getBlockEntity(blockPos) instanceof BaseCrateBlockEntity baseCrateBlockEntity) {
				openMenu(player, baseCrateBlockEntity);
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	protected abstract void openMenu(Player player, BaseCrateBlockEntity baseCrateBlockEntity);
}
