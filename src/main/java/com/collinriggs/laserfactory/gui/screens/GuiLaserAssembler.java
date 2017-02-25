package com.collinriggs.laserfactory.gui.screens;

import com.collinriggs.laserfactory.LaserFactory;
import com.collinriggs.laserfactory.blocks.machines.tiles.TileEntityLaserAssembler;
import com.collinriggs.laserfactory.gui.containers.ContainerLaserAssembler;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLaserAssembler extends GuiContainer {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(LaserFactory.MODID, "textures/gui/laserassembler.png");
	
	private TileEntityLaserAssembler tile;
	private InventoryPlayer playerInv;

	public GuiLaserAssembler(TileEntityLaserAssembler tile, InventoryPlayer playerInv) {
		super(new ContainerLaserAssembler(tile, playerInv));
		
		this.tile = tile;
		this.playerInv = playerInv;
		
		this.xSize = 175;
		this.ySize = 174;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	    this.mc.getTextureManager().bindTexture(BACKGROUND);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (tile.getCraftingProgress() > 0) {
        	this.drawTexturedModalRect(i + 98, j + 39, 176, 0, (int) (23 * (tile.getCraftingProgress() / 100d)), 16);
        }
        
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	    this.fontRendererObj.drawString(I18n.format("container.laserAssembler", new Object[0]), 8, 6, 4210752);
	    this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 81, 4210752);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;	
	}
	
}
