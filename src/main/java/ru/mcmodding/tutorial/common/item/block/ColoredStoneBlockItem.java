package ru.mcmodding.tutorial.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemDye;

public class ColoredStoneBlockItem extends ItemColored {
    public ColoredStoneBlockItem(Block block) {
        // Первый параметр указывает на блок, который будет устанавливаться, а что предмет имеет подтипы "id:damage"
        super(block, true);
        // Задаёт массив имён для ItemColored#getUnlocalizedName
        func_150943_a(ItemDye.field_150921_b);
    }
}