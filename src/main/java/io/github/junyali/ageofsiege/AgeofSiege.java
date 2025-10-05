package io.github.junyali.ageofsiege;

import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import io.github.junyali.ageofsiege.block.entity.AgeofSiegeBlockEntities;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import io.github.junyali.ageofsiege.potion.AgeofSiegePotions;
import io.github.junyali.ageofsiege.screen.AgeofSiegeMenuTypes;
import io.github.junyali.ageofsiege.villager.AgeofSiegeVillagers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AgeofSiege.MODID)
public class AgeofSiege {
    public static final String MODID = "ageofsiege";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AgeofSiege(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

		AgeofSiegeItems.register(modEventBus);
		AgeofSiegeBlocks.register(modEventBus);
		AgeofSiegePotions.register(modEventBus);
		AgeofSiegeBlockEntities.BLOCK_ENTITIES.register(modEventBus);
		AgeofSiegeMenuTypes.register(modEventBus);
	    AgeofSiegeVillagers.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
