package ru.mcmodding.tutorial.client.render.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.ModelWrapperDisplayList;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class RingItemRender implements IItemRenderer {
    private final ResourceLocation modelPath = new ResourceLocation(McModding.MOD_ID, "models/ring.obj");
    private final IModelCustom ringModel = new ModelWrapperDisplayList((WavefrontObject) AdvancedModelLoader.loadModel(modelPath));

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glTranslatef(2F, 0F, 0F);
        ringModel.renderAll();
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }
}