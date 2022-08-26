package ru.mcmodding.tutorial.common.block.fluid;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;
import ru.mcmodding.tutorial.common.item.tool.FluidCellItem;
import ru.mcmodding.tutorial.common.tile.TankFluidTile;

public class TankFluidBlock extends BlockContainer {
    public TankFluidBlock() {
        super(Material.iron);
        setBlockName("tank_fluid");
        setBlockTextureName(McModding.MOD_ID + ":tank_fluid");
        setCreativeTab(ModTab.INSTANCE);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }

        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TankFluidTile) {
            ItemStack held = activator.getHeldItem();

            if (held == null) {
                return false;
            }

            // Если в руке жидкостная ячейка, то заполняем её
            if (held.getItem() == ModItems.FLUID_CELL) {
                handleFillCell((TankFluidTile) tile, held);
            }
            // Если в руке пустое ведро, то заполняем его
            else if (held.getItem() == Items.bucket) {
                handleFillBucket((TankFluidTile) tile, activator, held);
            }
            // Заполняем резервуар жидкостью
            else {
                handleFillFluidTank((TankFluidTile) tile, activator, held);
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TankFluidTile();
    }

    private void handleFillCell(TankFluidTile tank, ItemStack held) {
        // Пробуем выкачать жидкость из резервуара без явного уменьшения кол-ва жидкости
        FluidStack fluidStack = tank.drain(ForgeDirection.UP, 1000, false);
        // Если стек не равен null и кол-во жидкости больше 0, то заливаем жидкость в жидкостную ячейку
        // Прежде чем заполнить, необходимо проверить, можем ли мы заполнить ячейку, без явного заполнения оной
        if (fluidStack != null && fluidStack.amount > 0 && ((FluidCellItem) held.getItem()).fill(held, fluidStack, false) > 0) {
            // Если всё прошло успешно, то необходимо явно выкачать из резервуара жидкость и заполнить ячейку
            fluidStack = tank.drain(ForgeDirection.UP, 1000, true);
            ((FluidCellItem) held.getItem()).fill(held, fluidStack, true);
            // Сохраняем и синхронизируем данные с клиентом
            tank.markDirty();
            tank.getWorldObj().markBlockForUpdate(tank.xCoord, tank.yCoord, tank.zCoord);
        }
    }

    private void handleFillBucket(TankFluidTile tank, EntityPlayer player, ItemStack held) {
        FluidStack fluidStack = tank.drain(ForgeDirection.UP, 1000, false);
        if (fluidStack != null && fluidStack.amount > 0) {
            fluidStack = tank.drain(ForgeDirection.UP, 1000, true);
            // Получаем заполненный контейнер с жидкостью по стеку жидкости
            ItemStack filled = FluidContainerRegistry.fillFluidContainer(fluidStack, held);
            if (filled != null && !player.inventory.addItemStackToInventory(filled)) {
                player.dropPlayerItemWithRandomChoice(filled, false);
            } else {
                player.openContainer.detectAndSendChanges();
            }

            if (!player.capabilities.isCreativeMode) {
                --held.stackSize;
            }

            tank.markDirty();
            tank.getWorldObj().markBlockForUpdate(tank.xCoord, tank.yCoord, tank.zCoord);
        }
    }

    private void handleFillFluidTank(TankFluidTile tile, EntityPlayer player, ItemStack held) {
        // Получаем жидкость из контейнера по стеку предмета
        FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(held);
        // Проверяем, что мы можем заполнить резервуар жидкостью, без явного заполнения
        if (tile.fill(ForgeDirection.UP, fluidStack, false) > 0) {
            // Заполняем резервуар явно
            tile.fill(ForgeDirection.UP, fluidStack, true);

            if (!player.capabilities.isCreativeMode) {
                ItemStack emptyContainer = FluidContainerRegistry.drainFluidContainer(held);
                if (!player.inventory.addItemStackToInventory(emptyContainer)) {
                    player.dropPlayerItemWithRandomChoice(emptyContainer, false);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
                --held.stackSize;
            }

            tile.markDirty();
            tile.getWorldObj().markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        }
    }
}