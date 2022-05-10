package ru.mcmodding.tutorial.common.item.tool;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

import java.util.Set;

public class RubySpade extends ItemTool {
    public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet(
            Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer,
            Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium
    );

    public RubySpade() {
        super(1F, ModItems.RUBY_TOOL_MATERIAL, HARVEST_BLOCKS);
        setUnlocalizedName("ruby_spade");
        setTextureName(McModding.MOD_ID + ":ruby_spade");
        setCreativeTab(ModTab.INSTANCE);

        setHarvestLevel("shovel", ModItems.RUBY_TOOL_MATERIAL.getHarvestLevel());
    }
}