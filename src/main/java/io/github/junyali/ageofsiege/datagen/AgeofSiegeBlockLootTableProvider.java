package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AgeofSiegeBlockLootTableProvider extends BlockLootSubProvider {
	protected AgeofSiegeBlockLootTableProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {
		// breaking block drops stuff idk
		dropSelf(AgeofSiegeBlocks.CRATE_BLOCK.get());
		add(AgeofSiegeBlocks.SALT_BLOCK.get(),
				block -> createMultipleOreDrops(AgeofSiegeBlocks.SALT_BLOCK.get(), AgeofSiegeItems.SALT.get(), 1, 4));
		dropOther(AgeofSiegeBlocks.COIN_PILE_BLOCK.get(), AgeofSiegeItems.COIN.get());
	}

	protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
		HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return this.createSilkTouchDispatchTable(pBlock,
				this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
						.apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks() {
		return AgeofSiegeBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
	}
}
