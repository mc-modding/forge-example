package ru.mcmodding.tutorial.common.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubyArmor extends ItemArmor {
    public RubyArmor(String elementName, int armorType) {
        super(ModItems.RUBY_ARMOR_MATERIAL, 0, armorType);
        setUnlocalizedName("ruby_" + elementName);
        setTextureName(McModding.MOD_ID + ":ruby_" + elementName);
        setCreativeTab(ModTab.INSTANCE);
    }

    /**
     * Формирует путь к текстуре брони
     * @param stack Стэк элемента брони который в данный момент надет на сущность
     * @param entity Сущность на которой надета броня
     * @param slot Слот в котором находится броня
     * @param type Подтип текстуры, может быть null или "overlay". Используется для наложения маски цвета для кожаной брони.
     * @return возвращает путь до текстуры брони
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return String.format("%s:textures/models/armor/ruby_layer_%d.png", McModding.MOD_ID, slot == 2 ? 2 : 1);
    }
}