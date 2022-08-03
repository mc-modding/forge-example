package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.mcmodding.tutorial.client.render.block.RubyBlockRenderer;
import ru.mcmodding.tutorial.common.block.RubyBlock;
import ru.mcmodding.tutorial.common.block.RubyOreBlock;

public class ModBlocks {
    public static final RubyBlock RUBY = new RubyBlock();
    public static final RubyOreBlock RUBY_ORE = new RubyOreBlock();

    public static int rubyRenderId = -1;

    public static void register() {
        GameRegistry.registerBlock(RUBY, "ruby_block");
        GameRegistry.registerBlock(RUBY_ORE, "ruby_ore");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        rubyRenderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(new RubyBlockRenderer());
    }
}