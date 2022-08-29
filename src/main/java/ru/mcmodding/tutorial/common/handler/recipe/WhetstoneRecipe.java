package ru.mcmodding.tutorial.common.handler.recipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.item.WhetstoneItem;

import java.util.concurrent.ThreadLocalRandom;

public class WhetstoneRecipe implements IRecipe {
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final Enchantment[] availableEnchantments = {
            Enchantment.sharpness, Enchantment.smite, Enchantment.baneOfArthropods
    };

    /**
     * Используется для проверки соответствия рецепта текущему инвентарю для крафта.
     *
     * @param inventory инвентарь для крафта.
     * @param world текущий мир.
     *
     * @return Возвращает логическое условие соответствия рецепта.
     */
    @Override
    public boolean matches(InventoryCrafting inventory, World world) {
        ItemStack whetstone = null;
        ItemStack tool = null;

        for (int slotId = 0, size = inventory.getSizeInventory(), notEmpty = 0; slotId < size; slotId++) {
            ItemStack stackInSlot = inventory.getStackInSlot(slotId);

            if (stackInSlot == null) {
                continue;
            }
            if (++notEmpty > 2) {
                return false;
            }

            if (whetstone == null && stackInSlot.getItem() instanceof WhetstoneItem) {
                whetstone = stackInSlot;
            } else if (tool == null && (stackInSlot.isItemEnchanted() || stackInSlot.isItemEnchantable())) {
                tool = stackInSlot;
                if (!stackInSlot.getItem().doesContainerItemLeaveCraftingGrid(tool)) {
                    return false;
                }
            }
        }
        return whetstone != null && tool != null && whetstone.getItem() != tool.getItem();
    }

    /**
     * Данный метод вызывается для получения результата крафта по текущему инвентарю крафта.
     *
     * @return Возвращает стек предмета.
     */
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory) {
        ItemStack whetstone = null;
        ItemStack tool = null;

        for (int slotId = 0, size = inventory.getSizeInventory(); slotId < size; slotId++) {
            ItemStack stackInSlot = inventory.getStackInSlot(slotId);

            if (stackInSlot == null) {
                continue;
            }

            if (whetstone == null && stackInSlot.getItem() instanceof WhetstoneItem) {
                whetstone = stackInSlot;
            } else if (stackInSlot.isItemEnchanted() || stackInSlot.isItemEnchantable()) {
                tool = stackInSlot;
                break;
            }
        }

        if (whetstone != null && tool != null && whetstone.getItem() != tool.getItem()) {
            if (!tool.getItem().doesContainerItemLeaveCraftingGrid(tool)) {
                return null;
            }

            if (whetstone.getItem() == ModItems.WHETSTONE && tool.isItemEnchantable()) {
                return handleDisenchantedTool(tool);
            } else if (whetstone.getItem() == ModItems.ANTI_ENCHANT_WHETSTONE && tool.isItemEnchanted()) {
                return handleEnchantedTool(tool);
            }
        }
        return null;
    }

    /**
     * Данный метод возвращает размер используемых слотов рецептом.
     *
     * @return Возвращает кол-во используемых слотов.
     */
    @Override
    public int getRecipeSize() {
        return 2;
    }

    /**
     * Данный метод возвращает стек предмета, который будет получен при крафте. Используется для заготовленного, выходящего
     * стека предмета, как в ShapedRecipe и т.п.
     *
     * @return Возвращает стек предмета.
     */
    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    /**
     * Вызывается для удаления чар с инструмента.
     *
     * @param tool стек инструмента.
     * @return Возвращает копию стека инструмента без чар.
     */
    private ItemStack handleEnchantedTool(ItemStack tool) {
        ItemStack toolCopy = tool.copy();
        toolCopy.getTagCompound().removeTag("ench");
        return toolCopy;
    }

    /**
     * Вызывается для зачаривания инструмента по заготовленным чарам.
     *
     * @param tool стек инструмента.
     * @return Возвращает копию стека инструмента с чарами.
     */
    private ItemStack handleDisenchantedTool(ItemStack tool) {
        ItemStack toolCopy = tool.copy();
        Enchantment enchantment = availableEnchantments[random.nextInt(availableEnchantments.length)];
        toolCopy.addEnchantment(enchantment, random.nextInt(enchantment.getMinLevel(), enchantment.getMaxLevel()));
        return toolCopy;
    }
}