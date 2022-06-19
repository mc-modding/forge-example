package ru.mcmodding.tutorial.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubyOreBlock extends Block {
    public RubyOreBlock() {
        super(Material.rock);
        setBlockName("ruby_ore");
        setBlockTextureName(McModding.MOD_ID + ":ruby_ore");
        setCreativeTab(ModTab.INSTANCE);
    }
}