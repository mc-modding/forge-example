package ru.mcmodding.tutorial.common.item.tool;

import net.minecraft.item.ItemSword;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubySword extends ItemSword {
    public RubySword() {
        super(ModItems.RUBY_TOOL_MATERIAL);
        setUnlocalizedName("ruby_sword");
        setTextureName(McModding.MOD_ID + ":ruby_sword");
        setCreativeTab(ModTab.INSTANCE);
    }
}