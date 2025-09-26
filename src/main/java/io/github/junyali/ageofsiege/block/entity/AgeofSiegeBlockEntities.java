package io.github.junyali.ageofsiege.block.entity;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AgeofSiegeBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AgeofSiege.MODID);

	public static final Supplier<BlockEntityType<CrateBlockEntity>> CRATE_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("crate_block_entity", () -> BlockEntityType.Builder.of(
					CrateBlockEntity::new,
					AgeofSiegeBlocks.CRATE_BLOCK.get()).build(null));
}
