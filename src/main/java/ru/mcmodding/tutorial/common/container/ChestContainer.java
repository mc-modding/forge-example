package ru.mcmodding.tutorial.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.mcmodding.tutorial.common.tile.ChestTile;

public class ChestContainer extends Container {

    private final ChestTile chest;

    public ChestContainer(ChestTile chest, InventoryPlayer playerInv) {
        this.chest = chest;

        // Инвентарь сундука
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(chest, col + row * 9, 8 + col * 18, 8 + row * 18));
            }
        }

        // Инвентарь игрока
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 145 + row * 18));
            }
        }

        // Хот-бар игрока
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(playerInv, col, 8 + col * 18, 203));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return chest.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack stack = null;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();

            if (slotIndex < chest.getSizeInventory()) { // Клик был выполнен в инвентаре сундука
                if (!mergeItemStack(slotStack, chest.getSizeInventory(), inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!mergeItemStack(slotStack, 0, chest.getSizeInventory(), false)) {
                return null;
            }

            if (slotStack.stackSize <= 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }
}
