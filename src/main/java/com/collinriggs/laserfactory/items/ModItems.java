package com.collinriggs.laserfactory.items;

import com.collinriggs.laserfactory.items.lasers.LesserLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item lesserLaser;
	
	public static void registerItems() {
		GameRegistry.register(lesserLaser = new LesserLaser());
	}
	
	public static void registerRenders() {
		registerRender(lesserLaser);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
