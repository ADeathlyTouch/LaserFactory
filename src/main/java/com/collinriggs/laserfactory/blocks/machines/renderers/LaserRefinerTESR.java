package com.collinriggs.laserfactory.blocks.machines.renderers;

import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserRefiner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class LaserRefinerTESR extends TileEntitySpecialRenderer<TileEntityLaserRefiner> {
	
	@Override
	public void renderTileEntityAt(TileEntityLaserRefiner te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		
		if (te.getItem() != null) {
			final EntityItem item = new EntityItem(te.getWorld(), 0, 0, 0, new ItemStack(te.getItem()));
			GlStateManager.translate(x, y, z);
			
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0, 0, 0, 0, partialTicks, false);
		}
	}
	
}
