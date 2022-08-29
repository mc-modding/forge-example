package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;
import ru.mcmodding.tutorial.client.render.item.RingItemRender;
import ru.mcmodding.tutorial.common.item.*;
import ru.mcmodding.tutorial.common.item.armor.RubyArmor;
import ru.mcmodding.tutorial.common.item.tool.*;

public class ModItems {

    /* Материалы */
    public static final Item.ToolMaterial RUBY_TOOL_MATERIAL =
            EnumHelper.addToolMaterial("mcmodding:ruby", 4, 1800, 16F, 5F, 30);
    public static final ItemArmor.ArmorMaterial RUBY_ARMOR_MATERIAL =
            EnumHelper.addArmorMaterial("mcmodding:ruby", 66, new int[]{5, 5, 5, 5}, 30);

    /* Редкость */
    public static final EnumRarity LEGENDARY_RARITY = EnumHelper.addRarity("mcmodding:legendary", EnumChatFormatting.RED, "Legendary");

    /* Предметы */
    public static final RingItem RING = new RingItem();
    public static final PaintCanItem PAINT_CAN = new PaintCanItem();
    public static final CherryItem CHERRY = new CherryItem();
    public static final RubyItem RUBY = new RubyItem();
    public static final BalloonItem BALLOON = new BalloonItem();

    /* Инструменты */
    public static final RubyAxe RUBY_AXE = new RubyAxe();
    public static final RubyHoe RUBY_HOE = new RubyHoe();
    public static final RubyPickaxe RUBY_PICKAXE = new RubyPickaxe();
    public static final RubySpade RUBY_SPADE = new RubySpade();
    public static final RubySword RUBY_SWORD = new RubySword();
    public static final RubyMultiTool RUBY_MULTI_TOOL = new RubyMultiTool();
    public static final FluidCellItem FLUID_CELL = new FluidCellItem();
    public static BucketItem JUICE_BUCKET;
    public static BucketItem STEAM_BUCKET;

    /* Броня */
    public static final RubyArmor RUBY_HELMET = new RubyArmor("helmet", 0, true);
    public static final RubyArmor RUBY_PLATE = new RubyArmor("plate", 1, true);
    public static final RubyArmor RUBY_LEGS = new RubyArmor("legs", 2, true);
    public static final RubyArmor RUBY_BOOTS = new RubyArmor("boots", 3, true);

    /* Другое */
    public static final WhetstoneItem WHETSTONE = new WhetstoneItem("whetstone", 50);
    public static final WhetstoneItem ANTI_ENCHANT_WHETSTONE = new WhetstoneItem("anti_enchant_whetstone", 70);

    static {
        RUBY_TOOL_MATERIAL.setRepairItem(new ItemStack(RUBY));
        RUBY_ARMOR_MATERIAL.customCraftingMaterial = RUBY;
    }

    public static void register() {
        GameRegistry.registerItem(RING, "ring");
        GameRegistry.registerItem(PAINT_CAN, "paint_can");
        GameRegistry.registerItem(CHERRY, "cherry");
        GameRegistry.registerItem(RUBY, "ruby");
        GameRegistry.registerItem(BALLOON, "balloon");

        GameRegistry.registerItem(RUBY_AXE, "ruby_axe");
        GameRegistry.registerItem(RUBY_HOE, "ruby_hoe");
        GameRegistry.registerItem(RUBY_PICKAXE, "ruby_pickaxe");
        GameRegistry.registerItem(RUBY_SPADE, "ruby_spade");
        GameRegistry.registerItem(RUBY_SWORD, "ruby_sword");
        GameRegistry.registerItem(RUBY_MULTI_TOOL, "ruby_multi_tool");
        GameRegistry.registerItem(FLUID_CELL, "fluid_cell");

        GameRegistry.registerItem(RUBY_HELMET, "ruby_helmet");
        GameRegistry.registerItem(RUBY_PLATE, "ruby_plate");
        GameRegistry.registerItem(RUBY_LEGS, "ruby_legs");
        GameRegistry.registerItem(RUBY_BOOTS, "ruby_boots");

        GameRegistry.registerItem(WHETSTONE, "whetstone");
        GameRegistry.registerItem(ANTI_ENCHANT_WHETSTONE, "anti_enchant_whetstone");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        MinecraftForgeClient.registerItemRenderer(RING, new RingItemRender());
    }
}