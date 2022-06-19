package ru.mcmodding.tutorial.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import ru.mcmodding.tutorial.common.handler.FuelHandler;
import ru.mcmodding.tutorial.common.handler.ModBlocks;
import ru.mcmodding.tutorial.common.handler.ModItems;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventListener());

        ModBlocks.register();
        ModItems.register();

        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}