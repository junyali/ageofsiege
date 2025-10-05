package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.block.AgeofSiegeBlocks;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AgeofSiegeRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public AgeofSiegeRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
		// crafting / smelting recipes go here
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AgeofSiegeItems.HARDTACK_BREAD.get(), 3)
				.pattern(" @ ")
				.pattern("###")
				.define('#', Items.WHEAT)
				.define('@', Items.SAND)
				.unlockedBy("has_wheat", has(Items.WHEAT)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AgeofSiegeItems.BLACK_BREAD.get(), 3)
				.pattern(" @ ")
				.pattern("###")
				.define('#', Items.WHEAT)
				.define('@', Items.CHARCOAL)
				.unlockedBy("has_wheat", has(Items.WHEAT)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AgeofSiegeBlocks.COIN_PILE_BLOCK.get(), 3)
				.pattern("###")
				.define('#', AgeofSiegeItems.COIN.get())
				.unlockedBy("has_coin", has(AgeofSiegeItems.COIN)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AgeofSiegeBlocks.CRATE_BLOCK.get(), 1)
				.pattern("###")
				.pattern("#@#")
				.pattern("###")
				.define('#', ItemTags.LOGS)
				.define('@', Items.BARREL)
				.unlockedBy("has_barrel", has(Items.BARREL)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AgeofSiegeBlocks.SALT_BLOCK.get(), 1)
				.requires(AgeofSiegeItems.SALT, 4)
				.unlockedBy("has_salt", has(AgeofSiegeItems.SALT)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AgeofSiegeItems.SALTED_MEAT.get(), 1)
				.requires(AgeofSiegeItems.SALT, 1)
				.requires(Items.BEEF, 1)
				.unlockedBy("has_salt", has(AgeofSiegeItems.SALT)).save(recipeOutput);

		// coin recipe may be changed later
		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(Items.IRON_INGOT),
				Ingredient.of(Items.GOLD_NUGGET),
				Ingredient.of(Items.COPPER_INGOT),
				RecipeCategory.MISC,
				AgeofSiegeItems.COIN.get())
				.unlocks("has_gold_nugget", has(Items.GOLD_NUGGET))
				.save(recipeOutput, "coin_smithing");
	}
}
