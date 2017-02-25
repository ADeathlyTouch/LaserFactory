package com.collinriggs.laserfactory.blocks.machines.tiles;

import javax.annotation.Nullable;

import com.collinriggs.laserfactory.blocks.TEHelper;
import com.collinriggs.laserfactory.crafting.MachineCraftingManager;
import com.collinriggs.laserfactory.items.inventory.Inventory;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

public class TileEntityLaserRefiner extends TileEntity implements ITickable {

	public static final String CRAFTING_KEY = "LaserRefiner";
	
	private Inventory inventory = new Inventory(1) {
		public int getInventoryStackLimit() {return 1;};
	};
	private int ticksRemaining = TICKS_TO_REFINE;
	private static final int TICKS_TO_REFINE = (30 /*seconds*/) * 20;
	
	public boolean addLaser(ItemStack stack) {
		if (!world.isRemote) {
			if (MachineCraftingManager.getInstance().isIngredient(CRAFTING_KEY, stack) && this.inventory.isEmpty()) {
				this.inventory.setInventorySlotContents(0, stack.splitStack(1));
	            TEHelper.sync(this);
	            this.markDirty();
	            return true;
			}
		}
		return false;
	}
	
	public void removeLaser() {
		if (!world.isRemote && !this.inventory.getStackInSlot(0).isEmpty()) {
			this.getWorld().spawnEntity(new EntityItem(this.getWorld(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(0)));
			inventory.setInventorySlotContents(0, ItemStack.EMPTY);
			this.ticksRemaining = TICKS_TO_REFINE; //Reset refining process.
            TEHelper.sync(this);
            this.markDirty();
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	private static final EnumParticleTypes particle = EnumParticleTypes.SPELL_WITCH;
	private static final double YDIFF = 0.3;
	
	@Override
	public void update() {
		if (!this.getWorld().isRemote && MachineCraftingManager.getInstance().isIngredient(CRAFTING_KEY, this.inventory.getStackInSlot(0))) {
			ticksRemaining--;
			if (ticksRemaining <= 0) {
				//Finished refining
				inventory.setInventorySlotContents(0,  MachineCraftingManager.getInstance().getResult(CRAFTING_KEY, inventory));
				this.ticksRemaining = TICKS_TO_REFINE;
			}
			this.markDirty();
			TEHelper.sync(this);
		}
		if (this.ticksRemaining < TICKS_TO_REFINE) {
			//Clientside, is refining.
			this.getWorld().spawnParticle(particle, this.getPos().getX() + 0.5, this.getPos().getY() + YDIFF, this.getPos().getZ() + 0.5, 0, 0.1, 0, new int[0]);
			this.getWorld().spawnParticle(particle, this.getPos().getX() + 0.4, this.getPos().getY() + YDIFF, this.getPos().getZ() + 0.5, 0, 0.1, 0, new int[0]);
			this.getWorld().spawnParticle(particle, this.getPos().getX() + 0.6, this.getPos().getY() + YDIFF, this.getPos().getZ() + 0.4, 0, 0.1, 0, new int[0]);
			this.getWorld().spawnParticle(particle, this.getPos().getX() + 0.5, this.getPos().getY() + YDIFF, this.getPos().getZ() + 0.6, 0, 0.1, 0, new int[0]);
			this.getWorld().spawnParticle(particle, this.getPos().getX() + 0.5, this.getPos().getY() + YDIFF, this.getPos().getZ() + 0.4, 0, 0.1, 0, new int[0]);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setTag("ItemStack", inventory.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
		compound.setInteger("TicksRemaining", ticksRemaining);
		
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		this.inventory.setInventorySlotContents(0, new ItemStack((NBTTagCompound) compound.getTag("ItemStack")));
		this.ticksRemaining = compound.getInteger("TicksRemaining");
		this.markDirty();
		TEHelper.sync(this);
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
