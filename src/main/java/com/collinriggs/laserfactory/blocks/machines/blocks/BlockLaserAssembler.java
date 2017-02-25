package com.collinriggs.laserfactory.blocks.machines.blocks;

import com.collinriggs.laserfactory.LaserFactory;
import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserAssembler;
import com.collinriggs.laserfactory.gui.GuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLaserAssembler extends Block implements ITileEntityProvider {

	public BlockLaserAssembler() {
		super(Material.IRON);
		this.setUnlocalizedName("laserAssembler");
		this.setRegistryName("laserassembler");
		this.setCreativeTab(LaserFactory.CREATIVE_TAB);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLaserAssembler();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos) == null || playerIn.isSneaking()) return false;
		playerIn.openGui(LaserFactory.instance, GuiHandler.LASER_ASSEMBLER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);

		if (worldIn.isRemote || worldIn.getTileEntity(pos) == null) return;
		TileEntityLaserAssembler te = (TileEntityLaserAssembler) worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, te.getInventory());
	}
	
}
