package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.FuelBurnTimeEvent;
import net.minecraftforge.event.world.BlockEvent;
import ru.mcmodding.tutorial.common.handler.event.SmelterEvent;

import java.util.LinkedList;
import java.util.List;

public class ForgeEventListener {
    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent event) {
        ItemStack inHand = event.harvester.getHeldItem();
        if (inHand == null || EnchantmentHelper.getEnchantmentLevel(ModEnchantments.melting.effectId, inHand) <= 0) {
            return;
        }
        List<ItemStack> smeltedDrop = new LinkedList<>();

        event.drops.removeIf(drop -> {
            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(drop);
            if (result != null) {
                smeltedDrop.add(result.copy());
                return true;
            }
            return false;
        });
        event.drops.addAll(smeltedDrop);
    }

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