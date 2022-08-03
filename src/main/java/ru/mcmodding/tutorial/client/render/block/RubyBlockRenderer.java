package ru.mcmodding.tutorial.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import ru.mcmodding.tutorial.common.handler.ModBlocks;

public class RubyBlockRenderer implements ISimpleBlockRenderingHandler {
    /**
     * Данный метод отвечает за отрисовку блока в инвентаре.
     *
     * @param block блок для которого применяется данная отрисовка
     * @param metadata метаданные блока
     * @param modelId уникальный идентификатор модели
     * @param renderer глобальный рендер блоков
     */
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    /**
     * Данный метод определяет необходимость, а также за отрисовку блока в мире.
     *
     * @param world     мир в котором находится блок.
     * @param x         позиция блока по X координате.
     * @param y         позиция блока по Y координате.
     * @param z         позиция блока по Z координате.
     * @param block     блок для которого применяется данная отрисовка.
     * @param modelId   уникальный идентификатор модели.
     * @param renderer  глобальный рендер блоков.
     * @return возвращает условие для отрисовки блока в мире
     */
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        // Сторона для получения текстуры
        int side = 0;
        // Получаем текстуру блока
        IIcon icon = renderer.getBlockIcon(block, world, x, y, z, side);

        Tessellator.instance.startDrawingQuads();

        // Отрисовка прямоугольника по координате X с инвертированием на обратную сторону
        renderer.renderFaceXNeg(block, x, y, z, icon);
        renderer.renderFaceXPos(block, x, y, z, icon);

        // Отрисовка прямоугольника по координате Y с инвертированием на обратную сторону
        renderer.renderFaceYNeg(block, x, y, z, icon);
        renderer.renderFaceYPos(block, x, y, z, icon);

        // Отрисовка прямоугольника по координате Z с инвертированием на обратную сторону
        renderer.renderFaceZNeg(block, x, y, z, icon);
        renderer.renderFaceZPos(block, x, y, z, icon);
        return true;
    }

    /**
     * Данный метод определяет необходимость отрисовки блока в 3D виде. При низкой настройки графики, блок не будет сплюснутым.
     *
     * @param modelId уникальный идентификатор модели.
     * @return возвращает условие для отрисовки модели в 3D виде.
     */
    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    /**
     * Уникальный идентификатор отрисовки блока. Не должен быть ниже 42 числа, иначе будет конфликт с ванильной отрисовкой блоков!
     *
     * @return возвращает уникальный номер.
     */
    @Override
    public int getRenderId() {
        return ModBlocks.rubyRenderId;
    }
}