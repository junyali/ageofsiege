package io.github.junyali.ageofsiege.screen;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.screen.custom.CrateMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AgeofSiegeMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENUS =
			DeferredRegister.create(BuiltInRegistries.MENU, AgeofSiege.MODID);

	public static final Supplier<MenuType<CrateMenu>> CRATE_MENU =
			MENUS.register("crate_menu", () -> IMenuTypeExtension.create(CrateMenu::new));

	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}
}
