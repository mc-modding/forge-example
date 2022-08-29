package ru.mcmodding.tutorial.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class WhetstoneItem extends Item {
    private final String name;

    @SideOnly(Side.CLIENT)
    private IIcon[] damagedIcons;

    public WhetstoneItem(String name, int maxDamage) {
        this.name = name;
        setMaxStackSize(1);
        setUnlocalizedName(name);
        setTextureName(String.format("%s:%s/default", McModding.MOD_ID, name));
        setCreativeTab(ModTab.INSTANCE);
        setMaxDamage(maxDamage);
        setNoRepair();
    }

    /**
     * Разрешить или запретить определенную комбинацию книги/предмета в качестве заклинания наковальни.
     *
     * @param stack стек предмета для зачаривания.
     * @param book книга с чарами.
     *
     * @return Возвращает логическое условие.
     */
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    /**
     * Возвращает true, если предмет содержит предмет-контейнер.
     *
     * @param stack текущий стек предмета.
     * @return Возвращает логическое условие.
     */
    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    /**
     * Возвращает стек с предметом-контейнером, который содержит в себе какую-либо информацию. Используется для получения
     * результата крафта.
     *
     * @param stack текущий стек предмета.
     *
     * @return Возвращает стек с предметом-контейнером.
     */
    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        ItemStack stackCopy = stack.copy();
        stackCopy.setItemDamage(stackCopy.getItemDamage() + 1);
        return stackCopy.getItemDamage() >= stackCopy.getMaxDamage() ? null : stackCopy;
    }

    /**
     * Возвращает условие, что предмет должен покинуть сетку крафта, при получении результата (предмет будет удалён).
     *
     * @param stack стек предмета.
     *
     * @return Возвращает логическое условие.
     */
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        super.registerIcons(register);
        damagedIcons = new IIcon[5];
        for (int count = 0; count < 5; count++) {
            damagedIcons[count] = register.registerIcon(String.format("%s:%s/damage_%d", McModding.MOD_ID, name, count));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int itemDamage) {
        int segment = MathHelper.floor_float(((float) itemDamage / getMaxDamage()) * 10) / 2;
        return itemDamage == 0 ? itemIcon : damagedIcons[segment];
    }
}