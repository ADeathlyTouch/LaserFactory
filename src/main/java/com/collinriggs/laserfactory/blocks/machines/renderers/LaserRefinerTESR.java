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
		
		if (!te.getInventory().isEmpty()) {
			ItemStack stackToRender = te.getInventory().getStackInSlot(0);
			stackToRender.setCount(1);
			final EntityItem item = new EntityItem(te.getWorld(), 0, 0, 0, stackToRender);;
			item.hoverStart = 0.0f;
			
			GlStateManager.translate(x, y, z);
			
			GlStateManager.rotate(90, 0, 0, 1);
			GlStateManager.translate(0.4, -1.2, 0.5);
			GlStateManager.scale(3, 2, 2);
			
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0, 0, 0, 0, 0, false);
		}
	}
	
}
