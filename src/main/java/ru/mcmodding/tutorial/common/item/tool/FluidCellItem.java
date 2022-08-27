package ru.mcmodding.tutorial.common.item.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;
import ru.mcmodding.tutorial.common.handler.ModTab;

import java.util.List;

public class FluidCellItem extends ItemFluidContainer {
    public FluidCellItem() {
        super(0, 5_000);
        setUnlocalizedName("fluid_cell");
        setTextureName("fluid_cell");
        setCreativeTab(ModTab.INSTANCE);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer holder, List tooltip, boolean isAdv) {
        FluidStack fluidStack = getFluid(stack);
        if (fluidStack != null) {
            tooltip.add(fluidStack.amount + " / " + capacity + " mB (" + fluidStack.getLocalizedName() + ")");
        } else {
            tooltip.add("<empty>");
        }
    }
}