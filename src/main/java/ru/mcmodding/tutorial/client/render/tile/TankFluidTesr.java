package ru.mcmodding.tutorial.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import ru.mcmodding.tutorial.common.tile.TankFluidTile;

import static org.lwjgl.opengl.GL11.*;

public class TankFluidTesr extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float delta) {
        TankFluidTile tankFluid = (TankFluidTile) tile;

        glPushMatrix();
        glTranslated(x + 1, y + 1, z - 0.001);
        glScalef(-0.01F, -0.01F, 1F);
        Minecraft.getMinecraft().fontRenderer.drawString(tankFluid.getAmount() + " / " + tankFluid.getCapacity() + " mB", 0, 0, 0xFFFFFF);
        glPopMatrix();
    }
}