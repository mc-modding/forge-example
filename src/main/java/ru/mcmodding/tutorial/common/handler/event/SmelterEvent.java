package ru.mcmodding.tutorial.common.handler.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Cancelable
public class SmelterEvent extends Event {
    private final Item ingredient;
    private ItemStack output;

    public SmelterEvent(Item ingredient) {
        this.ingredient = ingredient;
    }

    public Item getIngredient() {
        return ingredient;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }
}