package ru.mcmodding.tutorial.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubyBlock extends Block {
    public RubyBlock() {
        super(Material.rock);
        setBlockName("ruby");
        setBlockTextureName(McModding.MOD_ID + ":ruby");
        setCreativeTab(ModTab.INSTANCE);
    }
}