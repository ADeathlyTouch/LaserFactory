package com.collinriggs.laserfactory.crafting;

import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;

import com.collinriggs.laserfactory.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModCrafting {

	public static void registerRecipes() {
		//blocks
				
		//lasers
		addRecipe(new ItemStack(ModBlocks.lesserEnergyLaser), new Object[] {"III", "RWR", "III", 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'W', new ItemStack(Blocks.WOOL, 1, 3)});
		
		//misc
		addRecipe(new ItemStack(ModBlocks.glowingLapis), new Object[] {" G ", "GLG", " G ", 'G', Items.GLOWSTONE_DUST, 'L', Blocks.LAPIS_BLOCK});
		
		//items
		
	}
	
}
