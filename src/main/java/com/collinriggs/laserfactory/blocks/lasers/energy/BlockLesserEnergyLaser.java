package com.collinriggs.laserfactory.blocks.lasers.energy;

import com.collinriggs.laserfactory.blocks.lasers.EnumLaserLevel;
import com.collinriggs.laserfactory.blocks.lasers.energy.tile.TileEntityLesserEnergyLaser;

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