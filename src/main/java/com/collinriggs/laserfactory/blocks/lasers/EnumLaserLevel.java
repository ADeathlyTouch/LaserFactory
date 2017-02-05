package com.collinriggs.laserfactory.blocks.lasers;

public enum EnumLaserLevel {
	LESSER,
	COMMON,
	GREATER;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();	
	}
}
