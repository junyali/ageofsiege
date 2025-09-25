package io.github.junyali.ageofsiege.potion;

import io.github.junyali.ageofsiege.AgeofSiege;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AgeofSiegePotions {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, AgeofSiege.MODID);

	public static final Holder<Potion> MEAD_POTION = POTIONS.register("mead_potion",
			() -> new Potion(
					new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 0),
					new MobEffectInstance(MobEffects.CONFUSION, 7200, 0)
			));

	public static void register(IEventBus eventBus) {
		POTIONS.register(eventBus);
	}
}
