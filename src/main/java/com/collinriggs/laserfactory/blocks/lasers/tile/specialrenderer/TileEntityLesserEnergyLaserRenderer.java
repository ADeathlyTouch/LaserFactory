package com.collinriggs.laserfactory.blocks.lasers.tile.specialrenderer;

import com.collinriggs.laserfactory.blocks.lasers.BlockLesserEnergyLaser;
import com.collinriggs.laserfactory.blocks.lasers.tile.TileEntityLesserEnergyLaser;
import com.collinriggs.laserfactory.util.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityLesserEnergyLaserRenderer extends TileEntitySpecialRenderer<TileEntityLesserEnergyLaser> {
	
	@Override
	public void renderTileEntityAt(TileEntityLesserEnergyLaser te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		
		EnumFacing f = (EnumFacing) Minecraft.getMinecraft().world.getBlockState(te.getPos()).getProperties().get(BlockLesserEnergyLaser.FACING);
		
		
		if (te.getLaser_Length() > 0) {
			RenderHelper.renderBeamNoGlow(te, partialTicks, te.getLaser_Length() + .5D, 50, 255, 50, 255, 0.05, f);	
		}
		
	}
	
	@Override
	public boolean isGlobalRenderer(TileEntityLesserEnergyLaser te) {
		return true;
	}
	
}
