package com.collinriggs.laserfactory.blocks.lasers.sun;

import com.collinriggs.laserfactory.blocks.lasers.generic.BlockLaser;
import com.collinriggs.laserfactory.blocks.lasers.generic.EnumLaserLevel;
import com.collinriggs.laserfactory.blocks.lasers.generic.EnumLaserType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSunLaser extends BlockLaser {

	public BlockSunLaser() {
		super(EnumLaserLevel.NULL, EnumLaserType.SUN);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySunLaser();
	}
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

}
