package ru.mcmodding.tutorial.common.item;

import net.minecraft.item.Item;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RingItem extends Item {
    public RingItem() {
        setUnlocalizedName("ring");
        setTextureName(McModding.MOD_ID + ":ring");
        setMaxStackSize(1);
        setCreativeTab(ModTab.INSTANCE);
    }
}