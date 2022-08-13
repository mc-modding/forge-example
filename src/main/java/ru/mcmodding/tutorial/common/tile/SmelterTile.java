package ru.mcmodding.tutorial.common.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class SmelterTile extends TileEntity {
    private ItemStack stack;

    private static final String INV_TAG = "Inventory";

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (stack != null) {
            NBTTagCompound inventoryTag = new NBTTagCompound();
            stack.writeToNBT(inventoryTag);
            nbt.setTag(INV_TAG, inventoryTag);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.hasKey(INV_TAG, Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound inventoryTag = nbt.getCompoundTag(INV_TAG);
            stack = ItemStack.loadItemStackFromNBT(inventoryTag);
        }
    }

    /**
     * Данный метод вызывается каждый игровой тик. 20 тиков = 1 секунда
     */
    @Override
    public void updateEntity() {
        /*
         * Обязательно проверяем, что действия производятся на серверной стороне, затем проверяем, что имеется стек предмета.
         * Если игровое время в результате деления 100(5 сек) с остатком возвращает 0, то выполняется проверка на стек внутри плавильни.
         */
        if (!worldObj.isRemote && hasStack() && worldObj.getTotalWorldTime() % 100 == 0) {
            // Если предмет является блоком угольной руды, то создаём сущность предмета, которая содержит в себе стек с предметом "уголь"
            if (stack.getItem() == Item.getItemFromBlock(Blocks.coal_ore)) {
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, new ItemStack(Items.coal)));
                // Не забываем удалить стек
                stack = null;
                // А также не забывает сохранить данные
                markDirty();
            }
            // Если предмет является блоком железной руды, то создаём сущность предмета, которая содержит в себе стек с предметом "железный слиток"
            else if (stack.getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, new ItemStack(Items.iron_ingot)));
                stack = null;
                markDirty();
            }
        }
    }

    public boolean hasStack() {
        return stack != null;
    }

    public void handleInputStack(EntityPlayer player, ItemStack stack) {
        if (hasStack()) {
            if (!player.inventory.addItemStackToInventory(this.stack)) {
                player.dropPlayerItemWithRandomChoice(this.stack, false);
            } else {
                player.inventoryContainer.detectAndSendChanges();
            }
            this.stack = null;
        } else if (stack != null) {
            ItemStack copy = stack.copy();
            copy.stackSize = 1;
            this.stack = copy;
            --stack.stackSize;
        }
        markDirty();
    }
}