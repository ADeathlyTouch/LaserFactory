package com.collinriggs.laserfactory.blocks;

import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;
import static net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer;

import com.collinriggs.laserfactory.blocks.lasers.LesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.tile.TileEntityLesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.tile.specialrenderer.TileEntityLesserEnergyLaserRenderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block lesserEnergyLaser;
	
	public static void registerBlocks() {
		lesserEnergyLaser = new LesserEnergyLaser();
		
		registerBlock(lesserEnergyLaser);
	}
	
	public static void registerRenders() {
		registerRender(lesserEnergyLaser);
		
		bindTileEntitySpecialRenderer(TileEntityLesserEnergyLaser.class, new TileEntityLesserEnergyLaserRenderer());
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
	
}
