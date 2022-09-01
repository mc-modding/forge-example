package ru.mcmodding.tutorial.client.render.tile;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;
import ru.mcmodding.tutorial.common.tile.TankFluidTile;
import static org.lwjgl.opengl.GL11.*;

public class TankFluidTesr extends TileEntitySpecialRenderer {

    private static final float INSETS = 0.0125F;
    private final RenderBlocks renderBlocks = RenderBlocks.getInstance();

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float delta) {
        TankFluidTile tank = (TankFluidTile) tile;
        renderBlocks.blockAccess = tile.getWorldObj();

        FluidStack fluid = tank.getFluid();

        if (fluid != null) {
            glPushMatrix();
            glTranslated(x + 0.5, y, z + 0.5);
            renderLiquid(tank, fluid);
            glPopMatrix();
        }
    }

    private void renderLiquid(TankFluidTile tank, FluidStack fluid) {
        Block block = tank.getBlockType();
        IIcon icon = fluid.getFluid().getIcon(fluid);
        float level = (float) tank.getAmount() / (float) tank.getCapacity() * (1 - INSETS - INSETS);

        int bright = 200;
        if (renderBlocks.blockAccess != null) {
            bright = Math.max(200, block.getMixedBrightnessForBlock(renderBlocks.blockAccess, tank.xCoord, tank.yCoord, tank.zCoord));
        }

        glPushMatrix();
        glDisable(GL_LIGHTING);

        // Используем спрайт текстур всех блоков для рисования.
        // IIcon содержит информацию о положении текстуры нужного блока на спрайте.
        field_147501_a.field_147553_e.bindTexture(TextureMap.locationBlocksTexture);

        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(bright);
        tessellator.startDrawingQuads();

        // Размеры куба жидкости чуть меньше бака во избежание нахождения текстур в одной плоскости (эффект мерцающих текстур).
        renderBlocks.setRenderBounds(0.125D + INSETS, INSETS, 0.125D + INSETS, 0.875F - INSETS, INSETS + level, 0.875F - INSETS);
        renderBlocks.renderFaceYNeg(block, -0.5D, 0.0D, -0.5D, icon);
        renderBlocks.renderFaceYPos(block, -0.5D, 0.0D, -0.5D, icon);
        renderBlocks.renderFaceZNeg(block, -0.5D, 0.0D, -0.5D, icon);
        renderBlocks.renderFaceZPos(block, -0.5D, 0.0D, -0.5D, icon);
        renderBlocks.renderFaceXNeg(block, -0.5D, 0.0D, -0.5D, icon);
        renderBlocks.renderFaceXPos(block, -0.5D, 0.0D, -0.5D, icon);

        tessellator.draw();

        glEnable(GL_LIGHTING);
        glPopMatrix();
    }
}