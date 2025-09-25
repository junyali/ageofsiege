package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AgeofSiegeItemTagProvider extends ItemTagsProvider {
	// COMMON TAGS
	public static final TagKey<Item> SALT_BLOCK = commonTag("storage_blocks/salt");
	public static final TagKey<Item> SALT = commonTag("dusts/salt");

	public static final TagKey<Item> BREAD = commonTag("foods/bread");
	public static final TagKey<Item> RAW_MEAT = commonTag("foods/raw_meat");

	public AgeofSiegeItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTags, AgeofSiege.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		// tags go here!
		tag(SALT_BLOCK)
				.add(AgeofSiegeBlocks.SALT_BLOCK.asItem());
		tag(SALT)
				.add(AgeofSiegeItems.SALT.get());
		tag(BREAD)
				.add(AgeofSiegeItems.HARDTACK_BREAD.get())
				.add(AgeofSiegeItems.BLACK_BREAD.get());
		tag(RAW_MEAT)
				.add(AgeofSiegeItems.SALTED_MEAT.get());
	}

	private static TagKey<Item> commonTag(String name) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
	}
}
