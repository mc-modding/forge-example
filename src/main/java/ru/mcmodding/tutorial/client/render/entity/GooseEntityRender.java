package ru.mcmodding.tutorial.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.model.GooseModel;

public class GooseEntityRender extends RenderLiving {
    private static final ResourceLocation GOOSE_TEXTURE = new ResourceLocation(McModding.MOD_ID, "textures/entity/goose.png");

    public GooseEntityRender() {
        super(new GooseModel(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return GOOSE_TEXTURE;
    }
}