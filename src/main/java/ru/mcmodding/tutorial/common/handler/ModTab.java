package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.mcmodding.tutorial.McModding;

public class ModTab extends CreativeTabs {

    public static final ModTab INSTANCE = new ModTab();

    private ModTab() {
        super(McModding.MOD_ID);
        setBackgroundImageName("mcmodding.png");
    }

    /**
     * Возвращает предмет, используемый в качестве иконки вкладки.
     * При желании Вы можете переопределить <b>основной метод</b> {@link CreativeTabs#getIconItemStack()}
     * для возможности полной настройки {@link net.minecraft.item.ItemStack}
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ModItems.PAINT_CAN;
    }

    /**
     * Возвращает <b>damage</b> предмета иконки вкладки.
     * Используется в {@link CreativeTabs#getIconItemStack()}
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int func_151243_f() {
        return 11;
    }

    /**
     * Активирует поле поиска на вкладке
     */
    @Override
    public boolean hasSearchBar() {
        return true;
    }
}