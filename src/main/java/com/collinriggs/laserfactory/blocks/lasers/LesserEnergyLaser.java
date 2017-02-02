package com.collinriggs.laserfactory.blocks.lasers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class LesserEnergyLaser extends Block {

	public LesserEnergyLaser() {
		super(Material.PISTON);
		this.setUnlocalizedName("lesserEnergyLaser");
		this.setRegistryName("LesserEnergyLaser");
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setHardness(10);
		this.setResistance(200);
		this.setHarvestLevel("pickaxe", 2);
	}
	
}
