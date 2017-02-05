package com.collinriggs.laserfactory.items.lasers;

import com.collinriggs.laserfactory.LaserFactory;

import net.minecraft.item.Item;

public class ItemEnergyLaser extends Item {

	public ItemEnergyLaser(boolean refined) {
		this.setUnlocalizedName((!refined ? "un" : "") + "refinedEnergyLaser");
		this.setRegistryName((!refined ? "un" : "") + "refinedenergylaser");
		this.setCreativeTab(LaserFactory.CREATIVE_TAB);
	}
	
}
