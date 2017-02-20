package com.collinriggs.laserfactory.blocks.machines.tiles;

import javax.annotation.Nullable;

import com.collinriggs.laserfactory.crafting.laserrefiner.LaserRefinerCraftingManager;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityLaserRefiner extends TileEntity implements ITickable {

	private Item item = null;
	private int ticksRemaining = TICKS_TO_REFINE;
	private static final int TICKS_TO_REFINE = (30 /*seconds*/) * 20;
	
	public boolean addLaser(Item item) {
		if (!world.isRemote) {
			if (this.item == null && LaserRefinerCraftingManager.isIngredient(item)) {
				this.item = item;
				this.markDirty();
				return true;
			}
		}
		return false;
	}
	
	public void removeLaser() {
		if (!world.isRemote) {
			if (item != null) {
				world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(item)));
				item = null;
				this.markDirty();
			}
		}
	}
	
	@Override
	public void markDirty() {
		super.markDirty();
		IBlockState state = world.getBlockState(pos);
        world.notifyBlockUpdate(pos, state, state, 3);
	}
	
	@Override
	public void update() {
		if (LaserRefinerCraftingManager.isIngredient(item)) {
			//itemStack = new ItemStack(LaserRefinerCraftingManager.getResult(itemStack.getItem()), 1);
			//this.markDirty();
		}
	}
	
	public Item getItem() {
		return item;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		NBTTagCompound stackTag = new NBTTagCompound();
		(item==null?ItemStack.EMPTY:new ItemStack(item)).writeToNBT(stackTag);
		
		compound.setTag("ItemStack", stackTag);
		compound.setInteger("TicksRemaining", ticksRemaining);
		
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		ItemStack stack = new ItemStack((NBTTagCompound) compound.getTag("ItemStack"));
		if (stack.isEmpty())
			item = null;
		else
			item = stack.getItem();
		ticksRemaining = compound.getInteger("TicksRemaining");
		this.markDirty();
	}
	
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		System.out.println(this.getWorld().isRemote);
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

}
