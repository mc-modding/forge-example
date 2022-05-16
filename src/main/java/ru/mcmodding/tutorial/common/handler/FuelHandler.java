package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {
    @Override
    public int getBurnTime(ItemStack fuelStack) {
        if (fuelStack == null) {
            return 0;
        }
        final Item fuelItem = fuelStack.getItem();
        if (fuelItem == Items.fishing_rod) {
            return 350;
        } else if (fuelItem == Items.bed) {
            return 1100;
        } else if (fuelItem == Items.bowl) {
            return 900;
        }
        return 0;
    }
}