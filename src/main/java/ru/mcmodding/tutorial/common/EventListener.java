package ru.mcmodding.tutorial.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.FuelBurnTimeEvent;

public class EventListener {
    @SubscribeEvent
    public void onFuelBurnTime(FuelBurnTimeEvent event) {
        if (event.fuel == null) return;
        final Item fuelItem = event.fuel.getItem();
        if (fuelItem == Items.coal) {
            event.burnTime = 2600;
        }
    }
}