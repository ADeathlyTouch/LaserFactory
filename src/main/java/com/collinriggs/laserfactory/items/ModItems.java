package com.collinriggs.laserfactory.items;

import com.collinriggs.laserfactory.items.misc.ItemGlowingLapisShard;

import static net.minecraftforge.fml.common.registry.GameRegistry.register;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ModItems {
	
	
	
	//misc
	public static Item glowingLapisShard;
	
	public static void registerItems() {
		glowingLapisShard = new ItemGlowingLapisShard();
		
		register(glowingLapisShard);
	}
	
	public static void registerRenders() {
		registerRender(glowingLapisShard);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
