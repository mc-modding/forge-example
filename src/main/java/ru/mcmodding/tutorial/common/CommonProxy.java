package ru.mcmodding.tutorial.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.container.ChestContainer;
import ru.mcmodding.tutorial.common.handler.*;
import ru.mcmodding.tutorial.common.handler.packet.ServerMessagePacket;
import ru.mcmodding.tutorial.common.tile.ChestTile;

public class CommonProxy implements IGuiHandler {

    private static SimpleNetworkWrapper network;
    private static PacketHandler packetHandler;
    public static final int GUI_CHEST = 0;

    public void preInit(FMLPreInitializationEvent event) {
        // SimpleNetworkWrapper
        network = new SimpleNetworkWrapper(McModding.MOD_ID);
        network.registerMessage(new ServerMessagePacket.Handler(), ServerMessagePacket.class, 0, Side.SERVER);

        // FMLEventChannel как альтернатива SimpleNetworkWrapper
        // Вам нет никакой необходимости использовать сразу оба варианта в вашем моде!
        packetHandler = new PacketHandler();

        NetworkRegistry.INSTANCE.registerGuiHandler(McModding.instance, this);

        FMLCommonHandler.instance().bus().register(new FMLEventListener());
        MinecraftForge.EVENT_BUS.register(new ForgeEventListener());

        ModBlocks.register();
        ModEntities.register();
        ModItems.register();
        ModEnchantments.register();

        GameRegistry.registerFuelHandler(new FuelHandler());

        MinecraftForge.EVENT_BUS.register(new BucketHandler());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
        ModRecipes.registerOres();
        ModRecipes.registerRecipes();
    }

    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }

    public static PacketHandler getPacketHandler() {
        return packetHandler;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (id == GUI_CHEST) {
            return new ChestContainer((ChestTile) tile, player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null; // Реализован в ClientProxy
    }
}