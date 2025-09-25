package io.github.junyali.ageofsiege.item;

import net.minecraft.world.food.FoodProperties;

public class AgeofSiegeFoodProperties {
	public static final FoodProperties HARDTACK_BREAD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f)
			.build();

	public static final FoodProperties BLACK_BREAD = new FoodProperties.Builder().nutrition(4).saturationModifier(2.0f)
			.build();
}
