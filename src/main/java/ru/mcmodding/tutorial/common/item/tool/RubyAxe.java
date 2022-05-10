package ru.mcmodding.tutorial.common.item.tool;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

import java.util.Set;

public class RubyAxe extends ItemTool {
    public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet(
            Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin
    );

    public RubyAxe() {
        super(3F, ModItems.RUBY_MATERIAL, HARVEST_BLOCKS);
        setUnlocalizedName("ruby_axe");
        setTextureName(McModding.MOD_ID + ":ruby_axe");
        setCreativeTab(ModTab.INSTANCE);

        setHarvestLevel("axe", ModItems.RUBY_MATERIAL.getHarvestLevel());
    }

    @Override
    public float func_150893_a(ItemStack toolStack, Block block) {
        if (block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine)
            return super.func_150893_a(toolStack, block);
        return efficiencyOnProperMaterial;
    }
}