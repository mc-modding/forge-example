package ru.mcmodding.tutorial.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.container.ChestContainer;

public class ChestGui extends GuiContainer {

    private static final ResourceLocation bgTexture = new ResourceLocation(McModding.MOD_ID + ":textures/gui/chest.png");

    public ChestGui(ChestContainer container) {
        super(container);
        this.ySize = 227;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, 135, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(bgTexture);
        int left = (width - xSize) / 2;
        int top = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
    }
}
