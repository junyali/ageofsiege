package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
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
				.pattern("   ")
				.define('#', Items.WHEAT)
				.define('@', Items.SAND)
				.unlockedBy("has_wheat", has(Items.WHEAT)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AgeofSiegeItems.BLACK_BREAD.get(), 3)
				.pattern(" @ ")
				.pattern("###")
				.pattern("   ")
				.define('#', Items.WHEAT)
				.define('@', Items.CHARCOAL)
				.unlockedBy("has_wheat", has(Items.WHEAT)).save(recipeOutput);
	}
}
