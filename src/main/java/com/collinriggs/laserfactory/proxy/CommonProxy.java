package com.collinriggs.laserfactory.proxy;

import com.collinriggs.laserfactory.blocks.ModBlocks;
import com.collinriggs.laserfactory.items.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.registerBlocks();
		ModBlocks.registerTileEntities();
		
		ModItems.registerItems();
	}
	
	public void init(FMLInitializationEvent event) {
		
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
