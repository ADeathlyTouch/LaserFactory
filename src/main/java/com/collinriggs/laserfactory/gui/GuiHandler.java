package com.collinriggs.laserfactory.gui;

import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserAssembler;
import com.collinriggs.laserfactory.gui.containers.ContainerLaserAssembler;
import com.collinriggs.laserfactory.gui.screens.GuiLaserAssembler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int LASER_ASSEMBLER = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case LASER_ASSEMBLER:
			return new ContainerLaserAssembler((TileEntityLaserAssembler) world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case LASER_ASSEMBLER:
			return new GuiLaserAssembler((TileEntityLaserAssembler) world.getTileEntity(new BlockPos(x, y, z)), player.inventory);
		}
		return null;
	}

}
