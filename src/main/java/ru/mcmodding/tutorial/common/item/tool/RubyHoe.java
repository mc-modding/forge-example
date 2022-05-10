package ru.mcmodding.tutorial.common.item.tool;

import net.minecraft.item.ItemHoe;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubyHoe extends ItemHoe {
    public RubyHoe() {
        super(ModItems.RUBY_MATERIAL);
        setUnlocalizedName("ruby_hoe");
        setTextureName(McModding.MOD_ID + ":ruby_hoe");
        setCreativeTab(ModTab.INSTANCE);
    }
}