package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.mcmodding.tutorial.McModding;

@SideOnly(Side.CLIENT)
public class ModTab extends CreativeTabs {
    public static final ModTab INSTANCE = new ModTab();

    private ModTab() {
        super(McModding.MOD_ID);
        setBackgroundImageName("mcmodding.png");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.PAINT_CAN;
    }

    @Override
    public int func_151243_f() {
        return 11;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}