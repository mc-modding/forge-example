package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.mcmodding.tutorial.common.block.RubyBlock;
import ru.mcmodding.tutorial.common.block.RubyOreBlock;

public class ModBlocks {
    public static final RubyBlock RUBY = new RubyBlock();
    public static final RubyOreBlock RUBY_ORE = new RubyOreBlock();

    public static void register() {
        GameRegistry.registerBlock(RUBY, "ruby_block");
        GameRegistry.registerBlock(RUBY_ORE, "ruby_ore");
    }
}