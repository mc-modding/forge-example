package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.mcmodding.tutorial.client.render.item.RingItemRender;
import ru.mcmodding.tutorial.common.item.CherryItem;
import ru.mcmodding.tutorial.common.item.PaintCanItem;
import ru.mcmodding.tutorial.common.item.RingItem;

public class ModItems {
    public static final RingItem RING = new RingItem();
    public static final PaintCanItem PAINT_CAN = new PaintCanItem();
    public static final CherryItem CHERRY = new CherryItem();

    public static void register() {
        GameRegistry.registerItem(RING, "ring");
        GameRegistry.registerItem(PAINT_CAN, "paint_can");
        GameRegistry.registerItem(CHERRY, "cherry");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        MinecraftForgeClient.registerItemRenderer(RING, new RingItemRender());
    }
}