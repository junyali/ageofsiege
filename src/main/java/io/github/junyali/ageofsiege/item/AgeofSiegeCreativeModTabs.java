package io.github.junyali.ageofsiege.item;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AgeofSiegeCreativeModTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AgeofSiege.MODID);

	public static final Supplier<CreativeModeTab> AGEOFSIEGE_TAB = CREATIVE_MODE_TAB.register(AgeofSiege.MODID + "_tab",
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(AgeofSiegeBlocks.CRATE_BLOCK.asItem()))
					.title(Component.translatable("creativetab." + AgeofSiege.MODID + ".tab"))
					.displayItems((itemDisplayParameters, output) -> {
						// stuff goes here!
					}).build());

	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TAB.register(eventBus);
	}
}
