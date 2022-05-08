package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.mcmodding.tutorial.client.render.item.RingItemRender;
import ru.mcmodding.tutorial.common.item.RingItem;
import ru.mcmodding.tutorial.common.item.PaintCanItem;

public class ModItems {
    public static final RingItem RING = new RingItem();
    public static final PaintCanItem PAINT_CAN = new PaintCanItem();

    public static void register() {
        GameRegistry.registerItem(RING, "ring");
        GameRegistry.registerItem(PAINT_CAN, "paint_can");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        MinecraftForgeClient.registerItemRenderer(RING, new RingItemRender());
    }
}