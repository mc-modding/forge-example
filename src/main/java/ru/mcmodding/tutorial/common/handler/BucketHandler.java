package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import ru.mcmodding.tutorial.common.item.tool.BucketItem;

import java.util.HashMap;
import java.util.Map;

public class BucketHandler {
    // В данной карте будут храниться вёдра, которые мы будем получать по блоку жидкости
    private static final Map<Block, ItemBucket> buckets = new HashMap<>();

    /**
     * Вспомогательный метод необходимый для простой регистрации вёдер.
     *
     * @param name  название ведра
     * @param fluid жидкость к которой будет привязано ведро.
     * @return Возвращает объект ведра.
     */
    public static BucketItem registryBucket(String name, Fluid fluid) {
        BucketItem bucket = new BucketItem(name, fluid.getBlock());
        // Регистрируем экземпляр ведра
        GameRegistry.registerItem(bucket, name + "_bucket");
        // Регистрируем контейнер с жидкостью, т.е. ведро.
        FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(bucket), FluidContainerRegistry.EMPTY_BUCKET);
        // Добавляем к нашему блоку жидкости ведро, которое будет даваться при сборе жидкости.
        buckets.put(fluid.getBlock(), bucket);
        return bucket;
    }

    @SubscribeEvent
    public void onFillBucket(FillBucketEvent event) {
        // Получаем ведро по блоку, по которому было произведён клик.
        ItemBucket bucket = buckets.get(event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ));

        // Если ведро было обнаружено и метаданные блока равны 0(источник жидкости)
        if (bucket != null && event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ) == 0) {
            // Заменяем источник жидкости на блок воздуха(уничтожаем блок)
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            // Новый стек с ведром жидкости
            event.result = new ItemStack(bucket);
            // Говорим событию, что заполнение прошло успешно и нам необходимо получить ведро с жидкостью
            event.setResult(Event.Result.ALLOW);
        }
    }
}