package com.collinriggs.laserfactory.blocks.machines.tiles;

import javax.annotation.Nullable;

import com.collinriggs.laserfactory.blocks.TEHelper;
import com.collinriggs.laserfactory.items.inventory.Inventory;

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
		increaseCraftingProgress();
		if (craftingProgress > 100)
			resetCraftingProgress();
	}
	
	public int getCraftingProgress() {
		return craftingProgress;
	}
	
	public void increaseCraftingProgress() {
		this.craftingProgress++;
		this.markDirty();
		TEHelper.sync(this);
	}
	
	public void resetCraftingProgress() {
		this.craftingProgress = 0;
		this.markDirty();
		TEHelper.sync(this);
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
