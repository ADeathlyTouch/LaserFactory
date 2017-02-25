package com.collinriggs.laserfactory.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.collinriggs.laserfactory.items.inventory.Inventory;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MachineCraftingManager {
	
	private static final MachineCraftingManager INSTANCE = new MachineCraftingManager();
	private final Map<String, List<Recipe>> recipes = new HashMap<String, List<Recipe>>();
	
	public static MachineCraftingManager getInstance() {
		return INSTANCE;
	}
	
	public static Recipe addRecipe(String machine, ItemStack result, @Nonnull Object... ingredients) {
		Recipe recipe = new Recipe(result, ingredients);
		if (!INSTANCE.recipes.containsKey(machine)) {
			INSTANCE.recipes.put(machine, new ArrayList<Recipe>());
		}
		INSTANCE.recipes.get(machine).add(recipe);
		return recipe;
	}
	
	public ItemStack getResult(String machine, Inventory inventory) {
		if (!recipes.containsKey(machine)) {
			return ItemStack.EMPTY;
		}
		for (Recipe recipe : recipes.get(machine)) {
			if (inventory.contains(recipe.getIngredients())) {
				return recipe.getResult();
			}
		}
		return ItemStack.EMPTY;
	}
	
	public boolean isIngredient(String machine, ItemStack ingredient) {
		ItemStack stack = ingredient.copy();
		stack.setCount(1);
		for (Recipe recipe : recipes.get(machine)) {
			if (recipe.getIngredients().contains(stack))
				return true;
		}
		return false;
	}
	
	private static class Recipe {
		private final ItemStack result;
		private final Inventory ingredients;
		
		private Recipe(ItemStack result, @Nonnull Object... ingredients) {
			this.result = result;
			this.ingredients = new Inventory(ingredients.length);
			
			for (int i = 0; i < ingredients.length; i++) {
				Object obj = ingredients[i];
				if (obj instanceof ItemStack) {
					this.ingredients.setInventorySlotContents(i, (ItemStack) obj);
				} else
				if (obj instanceof Item) {
					this.ingredients.setInventorySlotContents(i, new ItemStack((Item) obj));
				} else
				if (obj instanceof Block) {
					this.ingredients.setInventorySlotContents(i,  new ItemStack((Block) obj));
				} else {
					this.ingredients.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
		
		private ItemStack getResult() {
			return result;
		}
		
		private Inventory getIngredients() {
			return ingredients;
		}
		
	}

}
