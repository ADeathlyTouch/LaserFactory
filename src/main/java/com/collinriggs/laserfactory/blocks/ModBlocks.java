package com.collinriggs.laserfactory.blocks;

import com.collinriggs.laserfactory.blocks.lasers.LesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.tile.TileEntityLesserEnergyLaser;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block lesserEnergyLaser;
	
	public static void registerBlocks() {
		lesserEnergyLaser = new LesserEnergyLaser();
		
		registerBlock(lesserEnergyLaser);
	}
	
	public static void registerRenders() {
		registerRender(lesserEnergyLaser);
	}
	
	public static void registerTileEntities() {
		registerTileEntity(TileEntityLesserEnergyLaser.class, "tile_entity_lesser_energy_laser");
	}
	
	private static void registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
	
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name) {
		GameRegistry.registerTileEntity(tileEntityClass, name);
	}
	
}
