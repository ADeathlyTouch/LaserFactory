package com.collinriggs.laserfactory.util;

import net.minecraft.item.Item;

public class ItemStackHelper {
	
	public static boolean areItemsEqual(Item item1, Item item2) {
		if (item1 == null)
			if (item2 == null)
				return true;
			else
				return false;
		if (item2 == null)
			return false;
		return item1.getRegistryName().toString().equals(item2.getRegistryName().toString());
	}

}
