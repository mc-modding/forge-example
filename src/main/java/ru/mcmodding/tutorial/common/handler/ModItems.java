package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;
import ru.mcmodding.tutorial.client.render.item.RingItemRender;
import ru.mcmodding.tutorial.common.item.CherryItem;
import ru.mcmodding.tutorial.common.item.PaintCanItem;
import ru.mcmodding.tutorial.common.item.RingItem;
import ru.mcmodding.tutorial.common.item.tool.*;

public class ModItems {
    /** Materials **/
    public static final Item.ToolMaterial RUBY_MATERIAL =
            EnumHelper.addToolMaterial("mcmodding:ruby", 4, 1800, 16F, 5F, 30);

    /** Items **/
    public static final RingItem RING = new RingItem();
    public static final PaintCanItem PAINT_CAN = new PaintCanItem();
    public static final CherryItem CHERRY = new CherryItem();

    /** Tools **/
    public static final RubyAxe RUBY_AXE = new RubyAxe();
    public static final RubyHoe RUBY_HOE = new RubyHoe();
    public static final RubyPickaxe RUBY_PICKAXE = new RubyPickaxe();
    public static final RubySpade RUBY_SPADE = new RubySpade();
    public static final RubySword RUBY_SWORD = new RubySword();
    public static final RubyMultiTool RUBY_MULTI_TOOL = new RubyMultiTool();

    public static void register() {
        GameRegistry.registerItem(RING, "ring");
        GameRegistry.registerItem(PAINT_CAN, "paint_can");
        GameRegistry.registerItem(CHERRY, "cherry");

        GameRegistry.registerItem(RUBY_AXE, "ruby_axe");
        GameRegistry.registerItem(RUBY_HOE, "ruby_hoe");
        GameRegistry.registerItem(RUBY_PICKAXE, "ruby_pickaxe");
        GameRegistry.registerItem(RUBY_SPADE, "ruby_spade");
        GameRegistry.registerItem(RUBY_SWORD, "ruby_sword");
        GameRegistry.registerItem(RUBY_MULTI_TOOL, "ruby_multi_tool");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        MinecraftForgeClient.registerItemRenderer(RING, new RingItemRender());
    }
}