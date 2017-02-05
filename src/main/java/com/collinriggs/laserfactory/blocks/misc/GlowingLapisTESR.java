package com.collinriggs.laserfactory.blocks.misc;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class GlowingLapisTESR extends TileEntitySpecialRenderer<GlowingLapisTE> {

	@Override
	public void renderTileEntityAt(GlowingLapisTE te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
		
		Color color = Color.RED;
		
		float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;
        float alpha = color.getAlpha() / 255.0f;

        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);
        
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        
        buffer.pos(0, 1, 0).color(red, green, blue, alpha).endVertex();
        buffer.pos(0, 2, 0).color(red, green, blue, alpha).endVertex();
        buffer.pos(1, 2, 0).color(red, green, blue, alpha).endVertex();
        buffer.pos(1, 1, 0).color(red, green, blue, alpha).endVertex();

        buffer.pos(0, 1, 1).color(red, green, blue, alpha).endVertex();
        buffer.pos(0, 2, 1).color(red, green, blue, alpha).endVertex();
        buffer.pos(1, 2, 1).color(red, green, blue, alpha).endVertex();
        buffer.pos(1, 1, 1).color(red, green, blue, alpha).endVertex();
        
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
	}
	
}
