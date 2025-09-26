package io.github.junyali.ageofsiege.screen.custom;

import io.github.junyali.ageofsiege.block.entity.CrateBlockEntity;
import io.github.junyali.ageofsiege.screen.AgeofSiegeMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class CrateMenu extends AbstractContainerMenu {
	public final CrateBlockEntity crateEntity;
	private final ContainerLevelAccess access;

	public CrateMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()));
	}

	public CrateMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity) {
		super(AgeofSiegeMenuTypes.CRATE_MENU.get(), containerId);

		if (blockEntity instanceof CrateBlockEntity crateBlockEntity) {
			this.crateEntity = crateBlockEntity;
		} else {
			throw new IllegalStateException("Incorrect entity passed!");
		}

		Level level = playerInventory.player.level();
		this.access = ContainerLevelAccess.create(level, crateEntity.getBlockPos());

		createSlots();
		createPlayerInventorySlots(playerInventory);
	}

	private void createSlots() {
		int rows = 3;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < 9; col++) {
				int index = row * 9 + col;
				this.addSlot(new SlotItemHandler(crateEntity.inventory, index, 8 + col * 18, 18 + row * 18));
			}
		}
	}

	private void createPlayerInventorySlots(Inventory playerInventory) {
		int crateRows = 3;
		int yOffset = 14 + 18 + (crateRows * 10);
		for (int row = 0; row < crateRows; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, yOffset + row * 18));
			}
		}
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInventory, col, 8 + col * 18, yOffset + 58));
		}
	}

	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player player, int slot) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slotObject = this.slots.get(slot);

		if (slotObject.hasItem()) {
			ItemStack originalStack = slotObject.getItem();
			newStack = originalStack.copy();

			int crateSize = 27;

			if (slot < crateSize) {
				if (!this.moveItemStackTo(originalStack, crateSize, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(originalStack, 0, crateSize, false)) {
					return ItemStack.EMPTY;
				}
			}

			if (originalStack.isEmpty()) {
				slotObject.set(ItemStack.EMPTY);
			} else {
				slotObject.setChanged();
			}
		}

		return newStack;
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return stillValid(this.access, player, crateEntity.getBlockState().getBlock());
	}
}
