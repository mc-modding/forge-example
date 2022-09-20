package ru.mcmodding.tutorial.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import ru.mcmodding.tutorial.client.gui.ChestGui;
import ru.mcmodding.tutorial.client.handler.KeyHandler;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.container.ChestContainer;
import ru.mcmodding.tutorial.common.handler.ModBlocks;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.command.CommandWhatIsIt;
import ru.mcmodding.tutorial.common.tile.ChestTile;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event); // Вызываем родительский метод из CommonProxy

        ModItems.registerRender();
        ModBlocks.registerRender();

        ClientCommandHandler.instance.registerCommand(new CommandWhatIsIt());

        KeyHandler keyHandler = new KeyHandler();
        keyHandler.register();
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (id) {
            case GUI_CHEST:
                return new ChestGui(new ChestContainer((ChestTile)tile, player.inventory));
            default:
                return null;
        }
    }
}