package com.collinriggs.laserfactory.gui.components;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MachineOutputSlot extends Slot {
	
	private IInventory ingredients;

	public MachineOutputSlot(IInventory inventoryIn, IInventory ingredients, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.ingredients = ingredients;
		
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;	
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		stack.onCrafting(thePlayer.getEntityWorld(), thePlayer, stack.getCount());
		for (int i = 0; i < ingredients.getSizeInventory(); i++) {
			ingredients.decrStackSize(i, 1);
		}
		return super.onTake(thePlayer, stack);
	}

}
