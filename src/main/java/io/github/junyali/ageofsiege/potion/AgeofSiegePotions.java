package io.github.junyali.ageofsiege.potion;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AgeofSiegePotions {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, AgeofSiege.MODID);

	public static void register(IEventBus eventBus) {
		POTIONS.register(eventBus);
	}
}
