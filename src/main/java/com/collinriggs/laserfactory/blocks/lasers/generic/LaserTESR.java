package com.collinriggs.laserfactory.blocks.lasers.generic;

import com.collinriggs.laserfactory.blocks.lasers.energy.blocks.BlockLesserEnergyLaser;
import com.collinriggs.laserfactory.util.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class LaserTESR<T extends TileEntityLaser> extends TileEntitySpecialRenderer<T> {
	
	private final int RED, GREEN, BLUE, ALPHA;
	
	public LaserTESR(int r, int g, int b, int a) {
		RED = r;
		GREEN = g;
		BLUE = b;
		ALPHA = a;
	}
	
	@Override
	public void renderTileEntityAt(T te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		
		EnumFacing facing = (EnumFacing) Minecraft.getMinecraft().world.getBlockState(te.getPos()).getProperties().get(BlockLesserEnergyLaser.FACING);
		
		if (te.getLaserLength() > 0) {
			RenderHelper.renderBeamNoGlow(te, partialTicks, te.getLaserLength() + .5D, RED, GREEN, BLUE, ALPHA, 0.05, facing);	
		}
		
	}
	
	@Override
	public boolean isGlobalRenderer(T te) {
		return true;
	}

}
