package io.github.junyali.ageofsiege.block;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AgeofSiegeBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AgeofSiege.MODID);

	// helper functions for registering blocks and item counterparts
	private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
		AgeofSiegeItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	// --

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
