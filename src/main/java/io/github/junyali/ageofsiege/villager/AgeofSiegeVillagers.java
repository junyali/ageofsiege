package io.github.junyali.ageofsiege.villager;

import com.google.common.collect.ImmutableSet;
import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AgeofSiegeVillagers {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, AgeofSiege.MODID);
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, AgeofSiege.MODID);

	public static final Holder<PoiType> MERCHANT_POI = POI_TYPES.register("merchant_poi",
			() -> new PoiType(ImmutableSet.copyOf(AgeofSiegeBlocks.CRATE_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

	public static final Holder<VillagerProfession> MERCHANT = VILLAGER_PROFESSIONS.register("merchant",
			() -> new VillagerProfession("merchant", holder -> holder.value() == MERCHANT_POI.value(),
					poiTypeHolder -> poiTypeHolder.value() == MERCHANT_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
					SoundEvents.VILLAGER_WORK_TOOLSMITH));

	public static void register(IEventBus eventBus) {
		POI_TYPES.register(eventBus);
		VILLAGER_PROFESSIONS.register(eventBus);
	}
}
