package ru.mcmodding.tutorial.common.block.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class JuiceFluidBlock extends BlockFluidClassic {
    public JuiceFluidBlock(Fluid fluid) {
        super(fluid, Material.water);
        // Привязываем жидкость к текущему блоку
        fluid.setBlock(this);
        setBlockName("juice");
        setBlockTextureName(McModding.MOD_ID + ":juice_still");
        setCreativeTab(ModTab.INSTANCE);
    }

    /**
     * Данный метод вызывается для проверки возможности вытеснения блоков жидкостью(как это делает вода с рычагами, редстоуном и т.п.)
     *
     * @param world     мир в котором установлен блок.
     * @param x         позиция блока по X координате.
     * @param y         позиция блока по Y координате.
     * @param z         позиция блока по Z координате.
     * @return Возвращает логическое условие.
     */
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        // Чтобы наша жидкость не заменяла воду, лаву или иные жидкости, добавим проверку, что материал блока не является жидкостью.
        return !world.getBlock(x, y, z).getMaterial().isLiquid() && super.canDisplace(world, x, y, z);
    }

    /**
     * Данный метод вызывается при попытке вытеснить блок.
     *
     * @param world     мир в котором установлен блок.
     * @param x         позиция блока для вытеснения по X координате.
     * @param y         позиция блока для вытеснения по Y координате.
     * @param z         позиция блока для вытеснения по Z координате.
     * @return Возвращает логическое условие.
     */
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        // Чтобы наша жидкость не заменяла воду, лаву или иные жидкости, добавим проверку, что материал блока не является жидкостью.
        return !world.getBlock(x, y, z).getMaterial().isLiquid() && super.displaceIfPossible(world, x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        // Получаем переданную жидкость через конструктор и добавляем ей текстуру неподвижной и подвижной жидкости.
        getFluid().setIcons(blockIcon, register.registerIcon(McModding.MOD_ID + ":juice_flow"));
    }
}