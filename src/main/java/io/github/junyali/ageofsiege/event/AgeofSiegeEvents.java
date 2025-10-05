package io.github.junyali.ageofsiege.event;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import io.github.junyali.ageofsiege.potion.AgeofSiegePotions;
import io.github.junyali.ageofsiege.villager.AgeofSiegeVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = AgeofSiege.MODID)
public class AgeofSiegeEvents {
	@SubscribeEvent
	public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();

		builder.addMix(Potions.AWKWARD, Items.HONEYCOMB, AgeofSiegePotions.MEAD_POTION);
	}

	@SubscribeEvent
	public static void addCustomTrades(VillagerTradesEvent event) {
		if (event.getType() == AgeofSiegeVillagers.MERCHANT.value()) {
			Int2ObjectMap<List< VillagerTrades.ItemListing>> trades = event.getTrades();

			trades.get(1).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.COIN.get(), 1),
					new ItemStack(Items.COAL, 3),
					12,
					3,
					0.05f
			));
			trades.get(1).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(Items.WHEAT, 8),
					new ItemStack(AgeofSiegeItems.COIN.get(), 1),
					8,
					3,
					0.05f
			));

			trades.get(2).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.COIN.get(), 1),
					new ItemStack(Items.OAK_LOG, 2),
					8,
					4,
					0.05f
			));
			trades.get(2).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.SALT.get(), 4),
					new ItemStack(AgeofSiegeItems.COIN.get(), 1),
					12,
					4,
					0.05f
			));

			trades.get(3).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.COIN.get(), 1),
					new ItemStack(AgeofSiegeItems.HARDTACK_BREAD.get(), 2),
					4,
					5,
					0.05f
			));
			trades.get(3).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.COIN.get(), 1),
					new ItemStack(AgeofSiegeItems.BLACK_BREAD.get(), 2),
					4,
					5,
					0.05f
			));
			trades.get(3).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.COIN.get(), 1),
					new ItemStack(AgeofSiegeItems.SALTED_MEAT.get(), 2),
					4,
					5,
					0.05f
			));

			trades.get(4).add((entity, randomSource) -> new MerchantOffer(
					new ItemCost(AgeofSiegeItems.COIN.get(), 8),
					new ItemStack(Items.DIAMOND, 1),
					2,
					6,
					0.05f
			));
		}
	}
}
