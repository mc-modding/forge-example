package ru.mcmodding.tutorial.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.common.handler.ModTab;

import java.util.List;

public class ColoredStoneBlock extends Block {
    public ColoredStoneBlock() {
        super(Material.rock);
        setBlockName("colored_stone");
        setBlockTextureName("stone");
        setCreativeTab(ModTab.INSTANCE);
    }

    /**
     * Данный метод обрабатывает входящие метаданные и возвращает цвет блока, отображаемого в инвентаре.
     *
     * @param metadata метаданные установленного блока
     * @return Возвращает цвет блока.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metadata) {
        return ItemDye.field_150922_c[metadata % ItemDye.field_150922_c.length];
    }

    /**
     * Данный метод обрабатывает входящие данные и возвращает цвет блока, установленного в мире, который будет домножен к основному.
     *
     * @param world мир в котором установлен блок.
     * @param x     позиция блока по X координате.
     * @param y     позиция блока по Y координате.
     * @param z     позиция блока по Z координате.
     * @return Возвращает множитель цвета.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        return ItemDye.field_150922_c[metadata % ItemDye.field_150922_c.length];
    }

    /**
     * Данный метод вызывается когда блок, устанавливается с помощью ItemBlock
     *
     * @param world     мир в котором устанавливается блок.
     * @param x         позиция на которой был установлен блок по X координате.
     * @param y         позиция на которой был установлен блок по Y координате.
     * @param z         позиция на которой был установлен блок по Z координате.
     * @param side      сторона с которой был установлен блок.
     * @param hitX      позицию на блоке, на которой производилось нажатие по X координате.
     * @param hitY      позицию на блоке, на которой производилось нажатие по Y координате.
     * @param hitZ      позицию на блоке, на которой производилось нажатие по Z координате.
     * @param metadata  исходные метаданные.
     * @return Возвращает обработанное значение метаданных до установки блока в мире. Во всех остальных случаях возвращаются исходные метаданные.
     */
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        return metadata;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List blocks) {
        for (int damage = 0, size = ItemDye.field_150922_c.length; damage < size; damage++) {
            blocks.add(new ItemStack(item, 1, damage));
        }
    }
}