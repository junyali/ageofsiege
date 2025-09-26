package io.github.junyali.ageofsiege.block.entity;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.custom.CrateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class CrateBlockEntity extends BlockEntity implements Container, MenuProvider {
	public final ItemStackHandler inventory;
	private int openCount = 0;

	public CrateBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AgeofSiegeBlockEntities.CRATE_BLOCK_ENTITY.get(), blockPos, blockState);
		this.inventory = new ItemStackHandler(27);
	}

	@Override
	public @NotNull Component getDisplayName() {
		return Component.translatable("container." + AgeofSiege.MODID +".crate");
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory, @NotNull Player player) {
		return null; // return custom menu class for crate
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < inventory.getSlots(); i++) {
			if (!inventory.getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @NotNull ItemStack getItem(int slot) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public @NotNull ItemStack removeItem(int slot, int amount) {
		ItemStack result = inventory.extractItem(slot, amount, false);
		if (!result.isEmpty()) {
			setChanged();
		}
		return result;
	}

	@Override
	public @NotNull ItemStack removeItemNoUpdate(int slot) {
		ItemStack stack = inventory.getStackInSlot(slot);
		inventory.setStackInSlot(slot, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setItem(int slot, @NotNull ItemStack stack) {
		inventory.setStackInSlot(slot, stack);
		setChanged();
	}

	@Override
	public void clearContent() {
		for (int i = 0; i < inventory.getSlots(); i++) {
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void startOpen(@NotNull Player player) {
		if (!this.remove && !player.isSpectator()) {
			if (this.openCount++ == 0) {
				assert this.level != null;
				this.level.setBlock(this.getBlockPos(),
						this.getBlockState().setValue(CrateBlock.OPEN, true), 3);
				this.level.playSound((Entity) null, this.getBlockPos(), SoundEvents.BARREL_OPEN,
						SoundSource.BLOCKS, 0.5f, (float) (this.level.random.nextFloat() * 0.1 + 0.9f));
			}
			assert this.level != null;
			this.level.scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
		}
	}

	@Override
	public void stopOpen(@NotNull Player player) {
		if (!this.remove && !player.isSpectator()) {
			if (--this.openCount <= 0) {
				assert this.level != null;
				this.level.setBlock(this.getBlockPos(),
						this.getBlockState().setValue(CrateBlock.OPEN, false), 3);
				this.level.playSound((Entity) null, this.getBlockPos(), SoundEvents.BARREL_CLOSE,
						SoundSource.BLOCKS, 0.5f, (float) (this.level.random.nextFloat() * 0.1 + 0.f));
			}
		}
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
		super.saveAdditional(tag, registries);
		tag.put("Inventory", inventory.serializeNBT(registries));
	}

	@Override
	protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
		super.loadAdditional(tag, registries);
		if (tag.contains("Inventory")) {
			inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider pRegistries) {
		return saveWithoutMetadata(pRegistries);
	}
}
