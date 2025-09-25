package io.github.junyali.ageofsiege.item;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AgeofSiegeItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgeofSiege.MODID);

	public static final DeferredItem<Item> SALT = ITEMS.register("salt",
			() -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> HARDTACK_BREAD = ITEMS.register("hardtack_bread",
			() -> new Item(new Item.Properties().food(AgeofSiegeFoodProperties.HARDTACK_BREAD)) {
				@Override
				public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
					tooltipComponents.add(Component.translatable("tooltip.ageofsiege.hardtack_bread.tooltip"));
					super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
				}
			});

	public static final DeferredItem<Item> BLACK_BREAD = ITEMS.register("black_bread",
			() -> new Item(new Item.Properties().food(AgeofSiegeFoodProperties.BLACK_BREAD)) {
				@Override
				public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
					tooltipComponents.add(Component.translatable("tooltip.ageofsiege.black_bread.tooltip"));
					super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
				}
			});

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
