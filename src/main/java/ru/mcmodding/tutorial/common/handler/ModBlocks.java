package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.block.RubyBlockRenderer;
import ru.mcmodding.tutorial.client.render.tile.SmelterTesr;
import ru.mcmodding.tutorial.common.block.*;
import ru.mcmodding.tutorial.common.item.block.ColoredStoneBlockItem;
import ru.mcmodding.tutorial.common.tile.SmelterTile;
import ru.mcmodding.tutorial.common.tile.StorageTile;

public class ModBlocks {
    public static final ColoredStoneBlock COLORED_STONE = new ColoredStoneBlock();
    public static final FaceBlock FACE = new FaceBlock();
    public static final RubyBlock RUBY = new RubyBlock();
    public static final RubyOreBlock RUBY_ORE = new RubyOreBlock();
    public static final SmelterBlock SMELTER = new SmelterBlock();
    public static final StorageBlock STORAGE = new StorageBlock();

    public static int rubyRenderId = -1;

    public static void register() {
        GameRegistry.registerBlock(COLORED_STONE, ColoredStoneBlockItem.class, "colored_stone");
        GameRegistry.registerBlock(FACE, "face");
        GameRegistry.registerBlock(RUBY, "ruby_block");
        GameRegistry.registerBlock(RUBY_ORE, "ruby_ore");
        GameRegistry.registerBlock(SMELTER, "smelter");
        GameRegistry.registerBlock(STORAGE, "storage");

        GameRegistry.registerTileEntity(SmelterTile.class, McModding.MOD_ID + ":smelter");
        GameRegistry.registerTileEntity(StorageTile.class, McModding.MOD_ID + ":storage");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        rubyRenderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(new RubyBlockRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(SmelterTile.class, new SmelterTesr());
    }
}