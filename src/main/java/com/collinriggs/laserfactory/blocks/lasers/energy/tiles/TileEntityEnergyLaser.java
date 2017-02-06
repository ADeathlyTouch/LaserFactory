package com.collinriggs.laserfactory.blocks.lasers.energy.tiles;

import com.collinriggs.laserfactory.blocks.lasers.generic.TileEntityLaser;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;

public abstract class TileEntityEnergyLaser extends TileEntityLaser {

	public final int MAX_ENERGY_OUTPUT;
	
	protected TileEntityEnergyLaser(int range, int maxOutput) {
		super(range);
		MAX_ENERGY_OUTPUT = maxOutput;
	}
	
	@Override
	protected boolean laserEndAtBlock(Block block) {
		return block instanceof BlockGrass;	
	}

}
