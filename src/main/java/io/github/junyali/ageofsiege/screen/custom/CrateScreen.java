package io.github.junyali.ageofsiege.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class CrateScreen extends AbstractContainerScreen<CrateMenu> {
	private static final ResourceLocation TEXTURE =
			ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/shulker_box.png");

	public CrateScreen(CrateMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);

		int crateRows = 3;
		this.imageHeight = 114 + crateRows * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
	}

	@Override
	public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}
}
