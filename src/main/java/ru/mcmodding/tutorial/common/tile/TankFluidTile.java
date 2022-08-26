package ru.mcmodding.tutorial.common.tile;

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
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        TileEntity tile = worldObj.getTileEntity(packet.func_148856_c(), packet.func_148855_d(), packet.func_148854_e());
        if (tile instanceof TankFluidTile) {
            ((TankFluidTile) tile).readExtendedData(packet.func_148857_g());
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return fluidTank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {
            return null;
        }
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluidTank.getFluid().getFluid() == fluid;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    public int getAmount() {
        return fluidTank.getFluidAmount();
    }

    public int getCapacity() {
        return fluidTank.getCapacity();
    }

    private void writeExtendedData(NBTTagCompound nbt) {
        fluidTank.writeToNBT(nbt);
    }

    private void readExtendedData(NBTTagCompound nbt) {
        fluidTank.readFromNBT(nbt);
    }
}