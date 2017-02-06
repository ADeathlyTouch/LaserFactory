package com.collinriggs.laserfactory.blocks.lasers.sun;

import com.collinriggs.laserfactory.blocks.lasers.generic.TileEntityLaser;

import net.minecraft.block.Block;

public class TileEntitySunLaser extends TileEntityLaser {
	
	public TileEntitySunLaser() {
		super(2, 2);
	}

	@Override
	protected boolean laserEndAtBlock(Block block) {
		return true;
	}
	
}
