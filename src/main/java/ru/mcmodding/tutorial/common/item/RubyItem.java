package ru.mcmodding.tutorial.common.item;

import net.minecraft.item.Item;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubyItem extends Item {
    public RubyItem() {
        setUnlocalizedName("ruby");
        setTextureName(McModding.MOD_ID + ":ruby");
        setCreativeTab(ModTab.INSTANCE);
    }
}