package com.collinriggs.laserfactory.blocks.machines.blocks;

import com.collinriggs.laserfactory.LaserFactory;
import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserRefiner;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLaserRefiner extends Block implements ITileEntityProvider {

	public BlockLaserRefiner() {
		super(Material.IRON);
		this.setUnlocalizedName("laserRefiner");
		this.setRegistryName("laserrefiner");
		this.setCreativeTab(LaserFactory.CREATIVE_TAB);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLaserRefiner();
	} 
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityLaserRefiner) {
			TileEntityLaserRefiner refiner = (TileEntityLaserRefiner) te;
			
			if (playerIn.isSneaking()) {
				System.out.println(refiner.getItemStack());
				return true;
			}
			
			ItemStack heldItem = playerIn.inventory.getCurrentItem();
			if (refiner.addLaser(heldItem.getItem())) {
				heldItem.grow(-1);
			} else {
				refiner.removeLaser();
			}
		}
		
		return true;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;	
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;	
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;	
	}
	
	private static final float MAGIC_NUMBER = 1F/16F;
	private static final AxisAlignedBB BOX = new AxisAlignedBB(1 * MAGIC_NUMBER, 0 * MAGIC_NUMBER, 1 * MAGIC_NUMBER, 15 * MAGIC_NUMBER, 7 * MAGIC_NUMBER, 15 * MAGIC_NUMBER);
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BOX;
	};
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOX;	
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;	
	}
	
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;	
	}

}
