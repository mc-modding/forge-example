package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.FuelBurnTimeEvent;
import ru.mcmodding.tutorial.common.handler.event.SmelterEvent;

public class ForgeEventListener {
    @SubscribeEvent
    public void onSmelter(SmelterEvent event) {
        // Если ингредиент яблоко, то выходящим стеком будет 5 золотых яблок
        if (event.getIngredient() == Items.apple) {
            event.setOutput(new ItemStack(Items.golden_apple, 5));
            // Отменяем событие, чтобы EventBus#post вернул true и выполнилось подмена логики
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onFuelBurnTime(FuelBurnTimeEvent event) {
        if (event.fuel == null) return;
        final Item fuelItem = event.fuel.getItem();
        if (fuelItem == Items.coal) {
            event.burnTime = 2600;
        }
    }
}