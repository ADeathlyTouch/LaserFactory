package com.collinriggs.laserfactory.blocks.lasers.energy.tile;

import com.collinriggs.laserfactory.blocks.lasers.energy.BlockLesserEnergyLaser;
import com.collinriggs.laserfactory.util.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class EnergyLaserTESR extends TileEntitySpecialRenderer<TileEntityEnergyLaser> {
	
	@Override
	public void renderTileEntityAt(TileEntityEnergyLaser te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		
		EnumFacing facing = (EnumFacing) Minecraft.getMinecraft().world.getBlockState(te.getPos()).getProperties().get(BlockLesserEnergyLaser.FACING);
		
		if (te.getLaserLength() > 0) {
			RenderHelper.renderBeamNoGlow(te, partialTicks, te.getLaserLength() + .5D, 0, 237, 255, 150, 0.05, facing);	
		}
		
	}
	
	@Override
	public boolean isGlobalRenderer(TileEntityEnergyLaser te) {
		return true;
	}

}
