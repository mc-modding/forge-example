package ru.mcmodding.tutorial.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.mcmodding.tutorial.common.handler.ModItems;

public class CommonProxy {
    public void pre(FMLPreInitializationEvent e) {
        ModItems.register();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void post(FMLPostInitializationEvent e) {

    }
}