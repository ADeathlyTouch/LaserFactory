package com.collinriggs.laserfactory.gui.containers;

import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserAssembler;
import com.collinriggs.laserfactory.crafting.MachineCraftingManager;
import com.collinriggs.laserfactory.gui.components.CraftingSlot;
import com.collinriggs.laserfactory.gui.components.MachineOutputSlot;
import com.collinriggs.laserfactory.items.inventory.Inventory;
import com.collinriggs.laserfactory.items.inventory.ResultInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLaserAssembler extends Container {
	
	private Inventory craftResult = new ResultInventory();
	private TileEntityLaserAssembler assembler;
		
	public ContainerLaserAssembler(TileEntityLaserAssembler assembler, InventoryPlayer playerInv) {
		this.assembler = assembler;
		
		//Assembler output, Slot 0
		this.addSlotToContainer(new MachineOutputSlot(craftResult, assembler.getInventory(), 0, 135, 40));
		
		//Assembler input, Slot 1-5
		this.addSlotToContainer(new CraftingSlot(this, assembler.getInventory(), 0, 48, 19)); //top
		this.addSlotToContainer(new CraftingSlot(this, assembler.getInventory(), 1, 27, 40)); //middle left
		this.addSlotToContainer(new CraftingSlot(this, assembler.getInventory(), 2, 48, 40)); //middle middle
		this.addSlotToContainer(new CraftingSlot(this, assembler.getInventory(), 3, 69, 40)); //middle right
		this.addSlotToContainer(new CraftingSlot(this, assembler.getInventory(), 4, 48, 61)); //bottom
		
		// Player Inventory, Slot 9-35, Slot IDs 6-32
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 93 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 33-41
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 151));
	    }
	    
	    this.onCraftMatrixChanged(assembler.getInventory());
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.craftResult.setInventorySlotContents(0, MachineCraftingManager.getInstance().getResult(TileEntityLaserAssembler.CRAFTING_KEY, assembler.getInventory()));
		System.out.println(MachineCraftingManager.getInstance().getResult(TileEntityLaserAssembler.CRAFTING_KEY, assembler.getInventory()).toString());
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;	
	}
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
		return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
	}
	
}
