package com.collinriggs.laserfactory.blocks.machines.tiles;

import com.collinriggs.laserfactory.blocks.TEHelper;
import com.collinriggs.laserfactory.items.inventory.Inventory;

import com.sun.istack.internal.Nullable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityLaserAssembler extends TileEntity implements ITickable {
	
	public static final String CRAFTING_KEY = "LaserAssembler";
	
	private Inventory inventory = new Inventory(5); //components
	private int craftingProgress = 0;
	
	@Override
	public void update() {
		this.craftingProgress++;
		if (craftingProgress > 100)
			this.craftingProgress = 0;
		this.markDirty();
		TEHelper.sync(this);
	}
	
	public int getCraftingProgress() {
		return craftingProgress;
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		ItemStackHelper.saveAllItems(compound, inventory.getList());
		
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		ItemStackHelper.loadAllItems(compound, inventory.getList());
	}
	
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }
	
}
