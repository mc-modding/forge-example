package ru.mcmodding.tutorial.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class TankFluidTile extends TileEntity implements IFluidHandler {

    private final FluidTank fluidTank = new FluidTank(10_000);

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        writeExtendedData(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        readExtendedData(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeExtendedData(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readExtendedData(packet.func_148857_g());
    }

    /**
     * Заполняет жидкость во внутренние резервуары, распределение полностью остается за IFluidHandler.
     *
     * @param from ориентация, из которой закачивается жидкость.
     * @param resource FluidStack, представляющий собой жидкость и максимальное количество жидкости, подлежащее заполнению.
     * @param doFill если значение false, заливка будет только имитироваться.
     * @return Кол-во жидкости, на которое был (или был бы, если бы было имитировано) заполнен резервуар.
     */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return fluidTank.fill(resource, doFill);
    }

    /**
     * Сливает жидкость из внутренних резервуаров, распределение полностью остается за IFluidHandler.
     *
     * @param from ориентация, в которую будет сливаться жидкость.
     * @param resource FluidStack, представляющий жидкость и максимальное количество жидкости, подлежащее сливу.
     * @param doDrain если значение false, то слив будет только имитироваться.
     * @return FluidStack, представляющий жидкость и кол-во, которое было (или было бы, если бы было имитировано) слито из резервуара.
     */
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {
            return null;
        }
        return fluidTank.drain(resource.amount, doDrain);
    }

    /**
     * Сливает жидкость из внутренних резервуаров, распределение полностью остается за IFluidHandler.
     * Этот метод не чувствителен к жидкости.
     *
     * @param from ориентация, в которую будет сливаться жидкость.
     * @param maxDrain максимальное количество жидкости для слива.
     * @param doDrain если значение false, то слив будет только имитироваться.
     * @return FluidStack, представляющий жидкость и кол-во, которое было (или было бы, если бы было имитировано) слито.
     */
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return fluidTank.drain(maxDrain, doDrain);
    }

    /**
     * Возвращает значение true, если данная жидкость может быть введена в заданном направлении.
     * Более формально, это должно возвращать значение true, если жидкость может поступать с заданного направления.
     *
     * @param from ориентация, в которую будет закачиваться жидкость.
     * @param fluid жидкость, которая будет поступать.
     * @return Возвращает логическое значение.
     */
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    /**
     * Возвращает значение true, если данная жидкость может быть извлечена из заданного направления.
     * Более формально, это должно возвращать значение true, если жидкость может выходить из заданного направления.
     *
     * @param from ориентация, в которую будет сливаться жидкость.
     * @param fluid жидкость, которая будет сливаться.
     * @return Возвращает логическое значение.
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    /**
     * Возвращает массив внутренних резервуаров. Эти объекты нельзя использовать
     * для манипулирования внутренними резервуарами. Смотрите {@link FluidTankInfo}.
     *
     * @param from ориентация, определяющая, какие резервуары следует запрашивать.
     * @return Информация для соответствующих внутренних резервуаров.
     */
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    /**
     * Кол-во жидкости находящееся в резервуаре.
     *
     * @return Возвращает кол-во жидкости.
     */
    public int getAmount() {
        return fluidTank.getFluidAmount();
    }

    /**
     * Вместимость резервуара.
     *
     * @return Возвращает кол-во вмещаемой жидкости в резервуаре.
     */
    public int getCapacity() {
        return fluidTank.getCapacity();
    }

    public FluidStack getFluid() {
        return fluidTank.getFluid();
    }

    private void writeExtendedData(NBTTagCompound nbt) {
        fluidTank.writeToNBT(nbt);
    }

    private void readExtendedData(NBTTagCompound nbt) {
        fluidTank.readFromNBT(nbt);
    }
}