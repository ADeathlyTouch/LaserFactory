package com.collinriggs.laserfactory.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;

public class TEHelper {
	
	public static void sync(TileEntity tileEntity) {
		if (tileEntity.getWorld() != null) {
			IBlockState state = tileEntity.getWorld().getBlockState(tileEntity.getPos());
			tileEntity.getWorld().notifyBlockUpdate(tileEntity.getPos(), state, state, 3);			
		}
	}

}
