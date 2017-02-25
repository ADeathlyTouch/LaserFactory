package com.collinriggs.laserfactory;

import com.collinriggs.laserfactory.blocks.ModBlocks;
import com.collinriggs.laserfactory.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LaserFactory.MODID, name = LaserFactory.MODNAME, version = LaserFactory.VERSION, dependencies = "after:jei")
public class LaserFactory {
	
    public static final String MODID = "laserfactory";
    public static final String MODNAME = "Laser Factory";
    public static final String VERSION = "1.0";
    
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.lesserEnergyLaser);
		}
    };
    
    @Instance
    public static LaserFactory instance;
    
    @SidedProxy(clientSide = "com.collinriggs.laserfactory.proxy.ClientProxy", serverSide = "com.collinriggs.laserfactory.proxy.ClientProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }

    
}
