package com.collinriggs.laserfactory.blocks.lasers.generic;

public enum EnumLaserType {
	ENERGY,
	SUN;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();	
	}
}
