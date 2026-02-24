package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.entity.GooseEntityRender;
import ru.mcmodding.tutorial.common.entity.GooseEntity;

public class ModEntities {
    private ModEntities() {
        throw new UnsupportedOperationException();
    }

    public static void register() {
        EntityRegistry.registerModEntity(GooseEntity.class, "goose", 0, McModding.instance, 80, 3, true);
        RenderingRegistry.registerEntityRenderingHandler(GooseEntity.class, new GooseEntityRender());
    }
}