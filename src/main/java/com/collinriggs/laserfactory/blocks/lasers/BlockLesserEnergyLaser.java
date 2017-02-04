package com.collinriggs.laserfactory.blocks.lasers;

import com.collinriggs.laserfactory.LaserFactory;
import com.collinriggs.laserfactory.blocks.BlockRotatable;
import com.collinriggs.laserfactory.blocks.lasers.tile.TileEntityLesserEnergyLaser;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLesserEnergyLaser extends BlockRotatable implements ITileEntityProvider {

	public BlockLesserEnergyLaser() {
		super(Material.IRON);
		this.setUnlocalizedName("lesserEnergyLaser");
		this.setRegistryName("lesserenergylaser");
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLesserEnergyLaser();
	}
	
}