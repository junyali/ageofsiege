package io.github.junyali.ageofsiege.screen.custom;

import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
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
	private final Level level;

	public CrateMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()));
	}

	public CrateMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity) {
		super(AgeofSiegeMenuTypes.CRATE_MENU.get(), containerId);
		this.level = playerInventory.player.level();

		if (blockEntity instanceof CrateBlockEntity crateBlockEntity) {
			this.crateEntity = crateBlockEntity;
		} else {
			throw new IllegalStateException("Incorrect entity passed!");
		}

		addSlots();
		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);
	}

	// yoinked from diesieben07 | https://github.com/diesieben07/SevenCommons
	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
	private static final int TE_INVENTORY_SLOT_COUNT = 27;

	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
		Slot sourceSlot = slots.get(pIndex);
		if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
		ItemStack sourceStack = sourceSlot.getItem();
		ItemStack copyOfSourceStack = sourceStack.copy();

		if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
					+ TE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY;
			}
		} else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
			if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
				return ItemStack.EMPTY;
			}
		} else {
			System.out.println("Invalid slotIndex:" + pIndex);
			return ItemStack.EMPTY;
		}
		if (sourceStack.getCount() == 0) {
			sourceSlot.set(ItemStack.EMPTY);
		} else {
			sourceSlot.setChanged();
		}
		sourceSlot.onTake(playerIn, sourceStack);
		return copyOfSourceStack;
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return stillValid(ContainerLevelAccess.create(level, crateEntity.getBlockPos()),
				player, AgeofSiegeBlocks.CRATE_BLOCK.get());
	}

	private void addSlots() {
		int rows = 3;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < 9; col++) {
				int index = row * 9 + col;
				this.addSlot(new SlotItemHandler(crateEntity.inventory, index, 8 + col * 18, 18 + row * 18));
			}
		}
	}

	private void addPlayerInventory(Inventory playerInventory) {
		for (int i = 0; i < 3; ++i) {
			for (int l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
			}
		}
	}

	private void addPlayerHotbar(Inventory playerInventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}
}
