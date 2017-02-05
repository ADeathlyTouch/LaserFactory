package com.collinriggs.laserfactory.blocks.lasers.energy.blocks;

import com.collinriggs.laserfactory.blocks.lasers.energy.tiles.TileEntityLesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.generic.EnumLaserLevel;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLesserEnergyLaser extends BlockEnergyLaser {

	public BlockLesserEnergyLaser() {
		super(EnumLaserLevel.LESSER);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLesserEnergyLaser();
	}
	
}