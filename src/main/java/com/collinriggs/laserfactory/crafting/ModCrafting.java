package com.collinriggs.laserfactory.crafting;

import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;
import static com.collinriggs.laserfactory.crafting.laserrefiner.LaserRefinerCraftingManager.addRecipe;

import com.collinriggs.laserfactory.blocks.ModBlocks;
import com.collinriggs.laserfactory.crafting.laserrefiner.LaserRefinerRecipe;
import com.collinriggs.laserfactory.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ModCrafting {

	public static void registerRecipes() {
		registerVanillaRecipes();
		registerLaserRefinerRecipes();
	}
	
	private static void registerVanillaRecipes() {
		//blocks
		addRecipe(new ItemStack(ModBlocks.lesserEnergyLaser), new Object[] {"III", "RWR", "III", 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'W', new ItemStack(Blocks.WOOL, 1, 3)});
		
		//misc
		addRecipe(new ItemStack(ModBlocks.glowingLapis), new Object[] {" G ", "GLG", " G ", 'G', Items.GLOWSTONE_DUST, 'L', Blocks.LAPIS_BLOCK});
	}
	
	private static void registerLaserRefinerRecipes() {
		addRecipe(new LaserRefinerRecipe(ModItems.unrefinedEnergyLaser, ModItems.refinedEnergyLaser));
	}
	
}
