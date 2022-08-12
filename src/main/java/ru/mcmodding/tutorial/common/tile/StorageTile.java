package ru.mcmodding.tutorial.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class StorageTile extends TileEntity {
    private ItemStack stack;

    // Чтобы избежать ошибок в названии тега, рекомендуется использовать константы
    private static final String INV_TAG = "Inventory";

    /**
     * Данный метод вызывается при записи данных Tile Entity в чанк. Мы не рекомендуем удалять вызов родительского метода,
     * так как это может привести к ошибке загрузки данных Tile Entity.
     *
     * @param nbt данные NBT в которых будет храниться информация о Tile Entity.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (stack != null) {
            NBTTagCompound inventoryTag = new NBTTagCompound();
            stack.writeToNBT(inventoryTag);
            nbt.setTag(INV_TAG, inventoryTag);
        }
    }

    /**
     * Данный метод вызывается при чтении данных Tile Entity из чанка. Мы не рекомендуем удалять вызов родительского метода,
     * так как это может привести к потере информации о Tile Entity во время загрузки Tile Entity.
     *
     * @param nbt данные NBT которые содержат информацию о Tile Entity.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.hasKey(INV_TAG, Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound inventoryTag = nbt.getCompoundTag(INV_TAG);
            stack = ItemStack.loadItemStackFromNBT(inventoryTag);
        }
    }

    /**
     * Будет ли Tile Entity обновляться. В нашем случае Tile Entity не имеет метода {@link TileEntity#updateEntity}, а значит обновлять
     * Tile Entity не нужно.
     *
     * @return Возвращает логическое условие.
     */
    @Override
    public boolean canUpdate() {
        return false;
    }

    /**
     * Вспомогательный метод, проверяющий наличие стека в Tile Entity.
     * @return Возвращает логическое условие.
     */
    public boolean hasStack() {
        return stack != null;
    }

    /**
     * Данный метод вызывается при активации блока и в зависимости от переданных данных, производит действие.
     *
     * @param player    игрок взаимодействующий с блоком
     * @param stack     стек предмета игрока в руке
     */
    public void handleInputStack(EntityPlayer player, ItemStack stack) {
        // Если в StorageTile есть стек, то выполнится действия со взятием предмета из StorageTile
        if (hasStack()) {
            // Если инвентарь заполнен, то предмет будет выброшен, в ином случае будет добавлен игроку в инвентарь.
            if (!player.inventory.addItemStackToInventory(this.stack)) {
                player.dropPlayerItemWithRandomChoice(this.stack, false);
            } else {
                // Иногда бывает, что предмет не отображается в инвентаре после активации блока, данный код исправляет это.
                player.inventoryContainer.detectAndSendChanges();
            }
            // Очищаем стек из StorageTile
            this.stack = null;
        }
        // Если стек в руке не равен null, то он будет положен в кол-ве 1 шт., в StorageTile
        else if (stack != null) {
            // Создаём копию стека и устанавливаем ей размер в 1 шт.
            ItemStack copy = stack.copy();
            copy.stackSize = 1;
            this.stack = copy;
            // Убираем один предмет из инвентаря игрока.
            --stack.stackSize;
        }
        // Не забываем пометить Tile Entity как "грязный", чтобы игра сохранила наши данные.
        markDirty();
    }
}