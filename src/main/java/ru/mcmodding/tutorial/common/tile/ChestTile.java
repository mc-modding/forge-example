package ru.mcmodding.tutorial.common.tile;

import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class ChestTile extends TileEntity implements IInventory {

    private final ItemStack[] items = new ItemStack[63];

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return items[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (items[slot] != null) {
            ItemStack stack;

            if (items[slot].stackSize <= amount) {
                stack = items[slot];
                items[slot] = null;
            } else {
                stack = items[slot].splitStack(amount);

                if (items[slot].stackSize <= 0) {
                    items[slot] = null;
                }
            }

            markDirty();
            return stack;
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (items[slot] != null) {
            ItemStack stack = items[slot];
            items[slot] = null;
            return stack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        items[slot] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }

        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "container.chest";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
    }

    @Override
    public void openInventory() {
        // NO-OP
    }

    @Override
    public void closeInventory() {
        // NO-OP
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList itemsTag = new NBTTagList();

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte)i);
                items[i].writeToNBT(tag);
                itemsTag.appendTag(tag);
            }
        }

        compound.setTag("Items", itemsTag);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList itemsTag = compound.getTagList("Items", 10);
        Arrays.fill(items, null); // Очищаем слоты на случай перезагрузки данных из NBT

        for (int i = 0; i < itemsTag.tagCount(); i++) {
            NBTTagCompound tag = itemsTag.getCompoundTagAt(i);
            int slot = tag.getByte("Slot") & 255;

            if (slot >= 0 && slot < items.length) {
                items[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }
}
