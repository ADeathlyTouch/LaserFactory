package com.collinriggs.laserfactory.gui.components;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class CraftingSlot extends Slot {
	
	private Container container;
	
	public CraftingSlot(Container container, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.container = container;
	}
	
	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
		container.onCraftMatrixChanged(this.inventory);
	}

}
