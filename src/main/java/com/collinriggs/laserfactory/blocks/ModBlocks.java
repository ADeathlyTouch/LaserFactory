package com.collinriggs.laserfactory.blocks;

import static net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer;
import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

import com.collinriggs.laserfactory.blocks.lasers.energy.blocks.BlockLesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.energy.renderers.EnergyLaserTESR;
import com.collinriggs.laserfactory.blocks.lasers.energy.tiles.TileEntityLesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.sun.BlockSunLaser;
import com.collinriggs.laserfactory.blocks.lasers.sun.SunLaserTESR;
import com.collinriggs.laserfactory.blocks.lasers.sun.TileEntitySunLaser;
import com.collinriggs.laserfactory.blocks.machines.blocks.BlockLaserAssembler;
import com.collinriggs.laserfactory.blocks.machines.blocks.BlockLaserRefiner;
import com.collinriggs.laserfactory.blocks.machines.renderers.LaserRefinerTESR;
import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserAssembler;
import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserRefiner;
import com.collinriggs.laserfactory.blocks.misc.blocks.BlockGlowingLapis;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

	//Lasers
	public static Block lesserEnergyLaser;
	public static Block sunLaser;
	
	//Machines
	public static Block laserRefiner;
	public static Block laserAssembler;
	
	//Misc
	public static Block glowingLapis;	
	
	public static void registerBlocks() {		
		registerBlock(lesserEnergyLaser = new BlockLesserEnergyLaser());
		registerBlock(sunLaser = new BlockSunLaser());
		
		registerBlock(laserRefiner = new BlockLaserRefiner());
		registerBlock(laserAssembler = new BlockLaserAssembler());
		
		registerBlock(glowingLapis = new BlockGlowingLapis());
	}
	
	public static void registerRenders() {
		registerRender(lesserEnergyLaser);
		registerRender(sunLaser);
		
		registerRender(laserRefiner);
		registerRender(laserAssembler);
		
		registerRender(glowingLapis);
		
		bindTileEntitySpecialRenderer(TileEntityLesserEnergyLaser.class, new EnergyLaserTESR());
		bindTileEntitySpecialRenderer(TileEntitySunLaser.class, new SunLaserTESR());
		bindTileEntitySpecialRenderer(TileEntityLaserRefiner.class, new LaserRefinerTESR());
	}
	
	public static void registerTileEntities() {
		registerTileEntity(TileEntityLesserEnergyLaser.class, "tile_entity_lesser_energy_laser");
		registerTileEntity(TileEntitySunLaser.class, "tile_entity_sun_laser");
		
		registerTileEntity(TileEntityLaserRefiner.class, "tile_entity_laser_refinery");
		registerTileEntity(TileEntityLaserAssembler.class, "tile_entity_laser_assembler");
	}
	
	private static void registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
	
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
}
