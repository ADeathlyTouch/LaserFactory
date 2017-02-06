package com.collinriggs.laserfactory.blocks.lasers.generic;

import org.apache.commons.lang3.StringUtils;

import com.collinriggs.laserfactory.LaserFactory;
import com.collinriggs.laserfactory.blocks.BlockRotatable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class BlockLaser extends BlockRotatable implements ITileEntityProvider {

	public BlockLaser(EnumLaserLevel laserLevel, EnumLaserType laserType) {
		super(Material.IRON);
		this.setUnlocalizedName(laserLevel.toString() 
				+ (laserLevel.toString().equals("") ? laserType.toString() : StringUtils.capitalize(laserType.toString()))
				+ "Laser");
		this.setRegistryName(laserLevel.toString()
				+ laserType.toString()
				+ "laser");
		this.setCreativeTab(LaserFactory.CREATIVE_TAB);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;	
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getProperties().get(FACING);
		if (facing == side)
			return false;
		else
			return true;
	}

}
