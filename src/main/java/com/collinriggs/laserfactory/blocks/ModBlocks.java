package com.collinriggs.laserfactory.blocks;

import com.collinriggs.laserfactory.blocks.lasers.LesserEnergyLaser;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block lesserEnergyLaser;
	
	public static void initialize() {
		lesserEnergyLaser = new LesserEnergyLaser();
		
		initialize(lesserEnergyLaser);
	}
	
	private static void initialize(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
	
	public static void registerRenders() {
		registerRender(lesserEnergyLaser);
	}
	
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
}
