package com.collinriggs.laserfactory.blocks.lasers;

import com.collinriggs.laserfactory.blocks.BlockRotatable;
import com.collinriggs.laserfactory.blocks.lasers.tile.TileEntityLesserEnergyLaser;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LesserEnergyLaser extends BlockRotatable {

	public LesserEnergyLaser() {
		super(Material.PISTON);
		this.setUnlocalizedName("lesserEnergyLaser");
		this.setRegistryName("lesserenergylaser");
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityLesserEnergyLaser();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		super.onBlockClicked(worldIn, pos, playerIn);
		System.out.println(worldIn.getTileEntity(pos));
	}
	
}