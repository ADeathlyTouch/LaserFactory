package com.collinriggs.laserfactory.blocks.lasers.generic;

public enum EnumLaserLevel {
	LESSER,
	COMMON,
	GREATER,
	NULL;
	
	@Override
	public String toString() {
		if (this == NULL)
			return "";
		return this.name().toLowerCase();	
	}
}
