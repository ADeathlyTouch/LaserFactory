package com.collinriggs.laserfactory.proxy;

import com.collinriggs.laserfactory.blocks.ModBlocks;
import com.collinriggs.laserfactory.items.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		ModBlocks.registerRenders();
		ModItems.registerRenders();
	}

}
