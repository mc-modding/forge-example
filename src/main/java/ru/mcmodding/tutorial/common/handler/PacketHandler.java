package ru.mcmodding.tutorial.common.handler;

import java.util.function.Consumer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ChatComponentText;
import ru.mcmodding.tutorial.McModding;

public class PacketHandler {

    public final FMLEventChannel channel;

    public PacketHandler() {
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(McModding.MOD_ID); // Имя канала
        channel.register(this); // Регистрируемся в шине событий нашего FMLEventChannel
    }

    /**
     * Обработчик пакетов, полученных клиентом.
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void handleClient(FMLNetworkEvent.ClientCustomPacketEvent event) {
        ByteBuf buf = event.packet.payload();
        byte id = buf.readByte();

        switch (id) {
            // У нас пока ничего не обрабатывается на клиенте.
        }
    }

    /**
     * Обработчик пакетов, полученных сервером.
     */
    @SubscribeEvent
    public void handleServer(FMLNetworkEvent.ServerCustomPacketEvent event) {
        ByteBuf buf = event.packet.payload();
        EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).playerEntity; // Определяем отправителя
        byte id = buf.readByte();

        switch (id) {
            case 0: {
                // Читаем данные в том же порядке
                String message = ByteBufUtils.readUTF8String(buf);
                int number = buf.readInt();
                // Отправляем сообщение игроку
                player.addChatMessage(new ChatComponentText("Вывожу текст \"" + message + "\" с числом \"" + number + "\""));
            }
            break;
        }
    }

    /**
     * Вспомогательный метод для быстрого создания пакета
     * @param id ID пакета
     * @param consumer Callback-функция записывающая данные
     */
    public static FMLProxyPacket makePacket(int id, Consumer<ByteBuf> consumer) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(id);
        consumer.accept(buf);
        return new FMLProxyPacket(buf, McModding.MOD_ID); // Второй параметр - имя канала
    }
}
