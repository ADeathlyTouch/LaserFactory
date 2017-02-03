package com.collinriggs.laserfactory.blocks.lasers.tile.specialrenderer;

import com.collinriggs.laserfactory.blocks.lasers.tile.TileEntityLesserEnergyLaser;
import com.collinriggs.laserfactory.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class TileEntityLesserEnergyLaserRenderer extends TileEntitySpecialRenderer<TileEntityLesserEnergyLaser> {

	private static final EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(ModItems.lesserLaser));
	
	@Override
	public void renderTileEntityAt(TileEntityLesserEnergyLaser te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		
		ITEM.hoverStart = 0F;
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.rotate(90F, 0, 0, 1);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, 0f, false);
		}
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		
		System.out.println();
		
	}
	
}
