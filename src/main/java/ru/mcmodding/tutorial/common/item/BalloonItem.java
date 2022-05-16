package ru.mcmodding.tutorial.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

import java.util.List;

public class BalloonItem extends Item {

    public BalloonItem() {
        setUnlocalizedName("balloon");
        setTextureName(McModding.MOD_ID + ":balloon");
        setCreativeTab(ModTab.INSTANCE);
        setHasSubtypes(true);// Указывает, что предмет содержит подтипы
    }

    /**
     * Возвращает нелокализованное название предмета.
     *
     * @param stack Стэк содержащий предмет класса BalloonItem
     * @return Возвращает нелокализованное название предмета формата: item.*unlocalizedName*
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + '_' + ItemDye.field_150921_b[stack.getItemDamage() % ItemDye.field_150921_b.length];
    }

    /**
     * Возвращает цвет предмета. Стандартный цвет предмета: 0xFFFFFF(16777215).
     *
     * @param stack      Стэк содержащий предмет класса BalloonItem
     * @param renderPass Текущий проход отрисовки
     * @return Цвет. Стандартный цвет: 0xFFFFFF(16777215).
     */
    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return ItemDye.field_150922_c[stack.getItemDamage() % ItemDye.field_150922_c.length];
    }

    /**
     * Данный метод заполняет список творческой вкладки новыми стэками, содержащие информация о предмете.
     *
     * @param item  Текущий предмет(в нашем случае объект класса BalloonItem)
     * @param tab   Творческая вкладка(сломано! Принимает только собственную вкладку в которой находится)
     * @param items Список стэков открытой вкладки
     */
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List items) {
        for (int damage = 0, size = ItemDye.field_150922_c.length; damage < size; damage++) {
            items.add(new ItemStack(item, 1, damage));
        }
    }
}