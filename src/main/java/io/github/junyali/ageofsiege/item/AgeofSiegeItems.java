package io.github.junyali.ageofsiege.item;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AgeofSiegeItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgeofSiege.MODID);

	public static final DeferredItem<Item> HARDTACK_BREAD = ITEMS.register("hardtack_bread",
			() -> new Item(new Item.Properties().food(AgeofSiegeFoodProperties.HARDTACK_BREAD)));

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
