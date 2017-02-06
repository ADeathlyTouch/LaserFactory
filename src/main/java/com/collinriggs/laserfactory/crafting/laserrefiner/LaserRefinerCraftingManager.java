package com.collinriggs.laserfactory.crafting.laserrefiner;

import java.util.ArrayList;
import java.util.List;

import com.collinriggs.laserfactory.util.ItemStackHelper;

import net.minecraft.item.Item;

public class LaserRefinerCraftingManager {
	
	private static List<LaserRefinerRecipe> recipes = new ArrayList<LaserRefinerRecipe>();
	
	public static void addRecipe(LaserRefinerRecipe recipe) {
		recipes.add(recipe);
	}
	
	public static boolean isIngredient(Item item) {
		for (LaserRefinerRecipe recipe : recipes) {
			if (ItemStackHelper.areItemsEqual(recipe.getIngredient(), item))
				return true;
		}
		return false;
	}
	
	public static Item getResult(Item item) {
		for (LaserRefinerRecipe recipe : recipes) {
			if (ItemStackHelper.areItemsEqual(recipe.getIngredient(), item))
				return recipe.getResult();
		}
		return null;
	}

}
