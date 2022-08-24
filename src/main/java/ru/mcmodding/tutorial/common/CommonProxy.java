package ru.mcmodding.tutorial.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.*;
import ru.mcmodding.tutorial.common.handler.packet.ServerMessagePacket;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        McModding.NETWORK.registerMessage(new ServerMessagePacket(), ServerMessagePacket.class, 0, Side.SERVER);

        FMLCommonHandler.instance().bus().register(new FMLEventListener());
        MinecraftForge.EVENT_BUS.register(new ForgeEventListener());

        ModBlocks.register();
        ModItems.register();

        GameRegistry.registerFuelHandler(new FuelHandler());

        MinecraftForge.EVENT_BUS.register(new BucketHandler());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}