package com.collinriggs.laserfactory.crafting.laserrefiner;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class LaserRefinerRecipe {
	
	private final Item ingredient;
	private final Item result;
	
	public LaserRefinerRecipe(Item ingredient, Item result) {
		this.ingredient = ingredient;
		this.result = result;
	}
	
	public LaserRefinerRecipe(Block ingredient, Item result) {
		this.ingredient = Item.getItemFromBlock(ingredient);
		this.result = result;
	}
	
	public LaserRefinerRecipe(Item ingredient, Block result) {
		this.ingredient = ingredient;
		this.result = Item.getItemFromBlock(result);
	}
	
	public LaserRefinerRecipe(Block ingredient, Block result) {
		this.ingredient = Item.getItemFromBlock(ingredient);
		this.result = Item.getItemFromBlock(result);
	}
	
	public Item getIngredient() {
		return ingredient;
	}
	
	public Item getResult() {
		return result;
	}

}
