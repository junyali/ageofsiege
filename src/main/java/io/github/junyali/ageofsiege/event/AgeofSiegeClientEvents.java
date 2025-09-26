package io.github.junyali.ageofsiege.event;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.screen.AgeofSiegeMenuTypes;
import io.github.junyali.ageofsiege.screen.custom.CrateScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = AgeofSiege.MODID, value = Dist.CLIENT)
public class AgeofSiegeClientEvents {
	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(AgeofSiegeMenuTypes.CRATE_MENU.get(), CrateScreen::new);
	}
}
