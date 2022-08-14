package ru.mcmodding.tutorial.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.ModelWrapperDisplayList;

import static org.lwjgl.opengl.GL11.*;

public class SmelterTesr extends TileEntitySpecialRenderer {
    private final ResourceLocation modelPath = new ResourceLocation(McModding.MOD_ID, "models/smelter.obj");
    private final IModelCustom smelterModel = new ModelWrapperDisplayList((WavefrontObject) AdvancedModelLoader.loadModel(modelPath));

    /**
     * Данный метод вызывается для отрисовки Tile Entity. Отрисовка происходит каждый кадр.
     *
     * @param tile текущий Tile Entity к которому прикреплён Tesr.
     * @param x позиция блока по X координате.
     * @param y позиция блока по Y координате.
     * @param z позиция блока по Z координате.
     * @param delta Разница во времени с момента последнего тика. Диапазон между 0 и 1.
     */
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float delta) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glTranslated(x + 0.5, y, z + 0.5);
        smelterModel.renderAll();
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }
}