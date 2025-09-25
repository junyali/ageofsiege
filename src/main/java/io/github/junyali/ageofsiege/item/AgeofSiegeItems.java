package io.github.junyali.ageofsiege.item;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AgeofSiegeItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgeofSiege.MODID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
