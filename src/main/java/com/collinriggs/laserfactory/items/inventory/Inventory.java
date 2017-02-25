package com.collinriggs.laserfactory.items.inventory;

import static net.minecraft.item.ItemStack.EMPTY;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Inventory implements IInventory {
	
	final int size;
	private NonNullList<ItemStack> inventory;
	final String name;
	
	public Inventory(int size, String name) {
		this.size = size;
		this.inventory = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
		this.name = name;
	}
	
	public Inventory(int size) {
		this(size, "Inventory");
	}
	
	public Inventory(NonNullList<ItemStack> inventory, String name) {
		this.size = inventory.size();
		this.inventory = inventory;
		this.name = name;
	}
	
	public Inventory(NonNullList<ItemStack> inventory) {
		this(inventory, "Inventory");
	}
	
	public Inventory(ItemStack... itemStacks) {
		this("Inventory", itemStacks);
	}
	
	public Inventory(String name, ItemStack... itemStacks) {
		this.name = name;
		this.size = itemStacks.length;
		this.inventory = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
		
		for (int i = 0; i < itemStacks.length; i++) {
			this.setInventorySlotContents(i, itemStacks[i]);
		}
	}
	
	public NonNullList<ItemStack> getList() {
		return inventory;
	}
	
	public static NonNullList<ItemStack> copy(NonNullList<ItemStack> inventory) {
		NonNullList<ItemStack> newInv = NonNullList.<ItemStack>withSize(inventory.size(), EMPTY);
		for (int i = 0; i < inventory.size(); i++) {
			newInv.set(i, inventory.get(i).copy());
		}
		return newInv;
	}
	
	public Inventory copy() {
		return new Inventory(copy(this.inventory), this.name);
	}
	
	public boolean contains(Inventory other) {
		Inventory toCheck = other.copy();
		
		for (ItemStack stack : toCheck.inventory) {
			if (!this.contains(stack)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(ItemStack other) {
		int count = other.getCount();
		
		for (int i = 0; i < this.size; i++) {
			ItemStack stack = this.getStackInSlot(i);
			if (stack.isItemEqual(other)) {
				count -= stack.getCount();
				if (count <= 0)
					return true;
			}
		}
		
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(name);
	}

	@Override
	public int getSizeInventory() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < this.size; i++) {
			if (!getStackInSlot(i).isEmpty())
				return false;
		}
		
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (isValidIndex(index))
			return this.inventory.get(index);
		return EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (!this.getStackInSlot(index).isEmpty()) {
	        ItemStack itemstack;

	        if (this.getStackInSlot(index).getCount() <= count) {
	            itemstack = this.getStackInSlot(index);
	            this.setInventorySlotContents(index, EMPTY);
	            return itemstack;
	        } else {
	            itemstack = this.getStackInSlot(index).splitStack(count);

	            if (this.getStackInSlot(index).getCount() <= 0) {
	                this.setInventorySlotContents(index, EMPTY);
	            } else {
	                this.setInventorySlotContents(index, this.getStackInSlot(index));
	            }
	            return itemstack;
	        }
	    } else {
	        return EMPTY;
	    }
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		this.setInventorySlotContents(index, EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (isValidIndex(index)) this.inventory.set(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override public int getField(int id) {return 0;}
	@Override public void setField(int id, int value) {}
	@Override public int getFieldCount() {return 0;}

	@Override
	public void clear() {
		
	}

	private boolean isValidIndex(int index) {
		return index >= 0 && index < this.getSizeInventory();
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		if (this.getName() != null)
			toReturn += "Name: " + this.getName();
		for (int i = 0; i < this.getSizeInventory(); i++) {
			toReturn += "\n\t" + i + ": " + this.getStackInSlot(i).toString();
		}
		return toReturn;
	}
	
}
