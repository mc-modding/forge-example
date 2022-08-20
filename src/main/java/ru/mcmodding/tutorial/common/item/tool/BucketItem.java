package ru.mcmodding.tutorial.common.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class BucketItem extends ItemBucket {
    public BucketItem(String name, Block fluidBlock) {
        super(fluidBlock);
        setUnlocalizedName(name + "_bucket");
        setMaxStackSize(1);
        setCreativeTab(ModTab.INSTANCE);
        setTextureName(McModding.MOD_ID + ':' + name + "_bucket");
    }
}