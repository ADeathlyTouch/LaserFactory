package com.collinriggs.laserfactory.items;

import static net.minecraftforge.fml.common.registry.GameRegistry.register;

import com.collinriggs.laserfactory.items.lasers.ItemEnergyLaser;
import com.collinriggs.laserfactory.items.misc.ItemGlowingLapisShard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class ModItems {
	
	//lasers
	public static Item unrefinedEnergyLaser;
	public static Item refinedEnergyLaser;
		
	public static Item glowingLapisShard;
	
	public static void registerItems() {
		unrefinedEnergyLaser = new ItemEnergyLaser(false);
		refinedEnergyLaser = new ItemEnergyLaser(true);
		
		glowingLapisShard = new ItemGlowingLapisShard();
		
		register(unrefinedEnergyLaser);
		register(refinedEnergyLaser);
		
		register(glowingLapisShard);
	}
	
	public static void registerRenders() {		
		registerRender(unrefinedEnergyLaser);
		registerRender(refinedEnergyLaser);
		
		registerRender(glowingLapisShard);
	}
	
	private static void registerRender(Item item) {
		registerRender(item, 0);
	}
	
	private static void registerRender(Item item, int meta) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
