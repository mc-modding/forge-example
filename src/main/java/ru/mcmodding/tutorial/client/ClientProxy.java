package ru.mcmodding.tutorial.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.handler.ModItems;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ModItems.registerRender();
    }
}