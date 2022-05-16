package ru.mcmodding.tutorial.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RingItem extends Item {

    public RingItem() {
        setUnlocalizedName("ring");
        setTextureName(McModding.MOD_ID + ":ring");
        setMaxStackSize(1);
        setCreativeTab(ModTab.INSTANCE);
    }

    /**
     * Обрабатывает редкость для предмета.
     *
     * @param stack Стэк содержащий предмет класса RingItem.
     * @return Возвращает редкость.
     */
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.LEGENDARY_RARITY;
    }
}