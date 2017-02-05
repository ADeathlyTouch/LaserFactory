package com.collinriggs.laserfactory.blocks.lasers.generic;

public enum EnumLaserLevel {
	LESSER,
	COMMON,
	GREATER;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();	
	}
}
