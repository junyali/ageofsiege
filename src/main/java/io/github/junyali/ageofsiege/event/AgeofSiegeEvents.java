package io.github.junyali.ageofsiege.event;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.potion.AgeofSiegePotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = AgeofSiege.MODID)
public class AgeofSiegeEvents {
	@SubscribeEvent
	public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();

		builder.addMix(Potions.AWKWARD, Items.HONEYCOMB, AgeofSiegePotions.MEAD_POTION);
	}
}
