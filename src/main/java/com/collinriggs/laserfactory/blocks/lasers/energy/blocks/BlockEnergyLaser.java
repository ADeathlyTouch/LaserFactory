package com.collinriggs.laserfactory.blocks.lasers.energy.blocks;

import com.collinriggs.laserfactory.blocks.lasers.generic.BlockLaser;
import com.collinriggs.laserfactory.blocks.lasers.generic.EnumLaserLevel;
import com.collinriggs.laserfactory.blocks.lasers.generic.EnumLaserType;

public abstract class BlockEnergyLaser extends BlockLaser {

	public BlockEnergyLaser(EnumLaserLevel laserLevel) {
		super(laserLevel, EnumLaserType.ENERGY);
	}

}
