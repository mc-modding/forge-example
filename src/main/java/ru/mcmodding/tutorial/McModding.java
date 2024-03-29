package ru.mcmodding.tutorial;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.handler.command.CommandPing;

@Mod(modid = McModding.MOD_ID)
public class McModding {

    public static final String MOD_ID = "mcmodding";

    @Mod.Instance(MOD_ID)
    public static McModding instance;

    @SidedProxy(
            clientSide = "ru.mcmodding.tutorial.client.ClientProxy",
            serverSide = "ru.mcmodding.tutorial.common.CommonProxy"
    )
    public static CommonProxy proxy;

    public static final EventBus MODDING_BUS = new EventBus();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandPing());
    }
}