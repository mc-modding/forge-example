package ru.mcmodding.tutorial.client.render.tile;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import ru.mcmodding.tutorial.common.tile.TankFluidTile;

import static org.lwjgl.opengl.GL11.*;

public class TankFluidTesr extends TileEntitySpecialRenderer {

    private static final float INSETS = 0.001F;
    private final RenderBlocks renderBlocks = RenderBlocks.getInstance();

    @Override
    public void func_147496_a(World world) {
        renderBlocks.blockAccess = world;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float delta) {
        TankFluidTile tank = (TankFluidTile) tile;

        FluidStack fluid = tank.getFluid();

        if (fluid != null) {
            glPushMatrix();
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            if (Minecraft.isAmbientOcclusionEnabled()) {
                glShadeModel(GL_SMOOTH);
            } else {
                glShadeModel(GL_FLAT);
            }
            renderLiquid(tank, fluid, x, y, z);
            glPopMatrix();
        }
    }

    private void renderLiquid(TankFluidTile tank, FluidStack fluid, double x, double y, double z) {
        Block block = tank.getBlockType();
        float level = (float) tank.getAmount() / (float) tank.getCapacity() * (1 - INSETS - INSETS);

        int bright = 200;
        if (renderBlocks.blockAccess != null) {
            bright = block.getMixedBrightnessForBlock(renderBlocks.blockAccess, tank.xCoord, tank.yCoord, tank.zCoord);
        }
        field_147501_a.field_147553_e.bindTexture(TextureMap.locationBlocksTexture);

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setTranslation(x - tank.xCoord, y - tank.yCoord, z - tank.zCoord);
        tessellator.setBrightness(bright);
        tessellator.setNormal(1.0F, 1.0F, 1.0F);

        renderBlocks.setOverrideBlockTexture(fluid.getFluid().getIcon(fluid));
        // Размеры куба жидкости чуть меньше бака во избежание нахождения текстур в одной плоскости (z-fighting).
        renderBlocks.setRenderBounds(0.125D + INSETS, INSETS, 0.125D + INSETS, 0.875F - INSETS, INSETS + level, 0.875F - INSETS);

        renderBlocks.renderStandardBlock(block, tank.xCoord, tank.yCoord, tank.zCoord);
        renderBlocks.clearOverrideBlockTexture();

        tessellator.draw();
        tessellator.setTranslation(0.0, 0.0, 0.0);
    }
}