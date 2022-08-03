package ru.mcmodding.tutorial.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.handler.ModBlocks;
import ru.mcmodding.tutorial.common.handler.ModItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event); // Вызываем родительский метод из CommonProxy

        ModItems.registerRender();
        ModBlocks.registerRender();
    }
}