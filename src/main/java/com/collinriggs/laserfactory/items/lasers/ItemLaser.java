package com.collinriggs.laserfactory.items.lasers;

import org.apache.commons.lang3.StringUtils;

import com.collinriggs.laserfactory.LaserFactory;
import com.collinriggs.laserfactory.blocks.lasers.generic.EnumLaserType;

import net.minecraft.item.Item;

public class ItemLaser extends Item {

	public ItemLaser(boolean refined, EnumLaserType laserType) {
		this.setUnlocalizedName((!refined ? "un" : "") + "refined" + StringUtils.capitalize(laserType.toString()) + "Laser");
		this.setRegistryName((!refined ? "un" : "") + "refined" + laserType.toString() + "laser");
		this.setCreativeTab(LaserFactory.CREATIVE_TAB);
	}
	
}
