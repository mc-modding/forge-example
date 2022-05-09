package ru.mcmodding.tutorial.common.item;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import ru.mcmodding.tutorial.McModding;

public class CherryItem extends ItemFood {
    public CherryItem() {
        super(2, 5F, false);
        setUnlocalizedName("cherry");
        setTextureName(McModding.MOD_ID + ":cherry");
        setPotionEffect(Potion.moveSlowdown.id, 15, 2, 0.5F);
    }
}