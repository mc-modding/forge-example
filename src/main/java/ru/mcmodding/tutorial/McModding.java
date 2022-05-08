package ru.mcmodding.tutorial;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.mcmodding.tutorial.common.CommonProxy;

@Mod(modid = McModding.MOD_ID)
public class McModding {
    public static final String MOD_ID = "mcmodding";

    @SidedProxy(
            clientSide = "ru.mcmodding.tutorial.client.ClientProxy",
            serverSide = "ru.mcmodding.tutorial.common.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void pre(FMLPreInitializationEvent e) {
        proxy.pre(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void post(FMLPostInitializationEvent e) {
        proxy.post(e);
    }
}