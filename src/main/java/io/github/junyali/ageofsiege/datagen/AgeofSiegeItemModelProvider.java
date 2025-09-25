package io.github.junyali.ageofsiege.datagen;

import io.github.junyali.ageofsiege.AgeofSiege;
import io.github.junyali.ageofsiege.item.AgeofSiegeItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class AgeofSiegeItemModelProvider extends ItemModelProvider {
	public AgeofSiegeItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, AgeofSiege.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		// register item models here
		basicItem(AgeofSiegeItems.HARDTACK_BREAD.get());
		basicItem(AgeofSiegeItems.BLACK_BREAD.get());
		basicItem(AgeofSiegeItems.SALT.get());
		basicItem(AgeofSiegeItems.SALTED_MEAT.get());
	}
}
