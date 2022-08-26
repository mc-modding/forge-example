package ru.mcmodding.tutorial.common.handler.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

public class ServerMessagePacket implements IMessage {

    // Набор полей данных пакета
    private String message;
    private int number;

    /**
     * Наличие конструктора по-умолчанию (без параметров) обязательно, через него Forge
     * создаёт экземпляр пакера каждый раз при получении от другой стороны.
     */
    public ServerMessagePacket() {
    }

    /**
     * Будет использоваться для формирования пакета по параметрам. Наличие этого конструктора не обязательно,
     * можно напрямую записывать значения в поля.
     *
     * @param message Сообщения, которое будет выводиться на серверной стороне.
     * @param number Число, которое будет выводиться на серверной стороне.
     */
    public ServerMessagePacket(String message, int number) {
        this.message = message;
        this.number = number;
    }

    /**
     * Читает данные пакета из {@link ByteBuf} при получении.
     *
     * @see <a href="https://netty.io/4.0/api/io/netty/buffer/ByteBuf.html">ByteBuf</a>
     * @param buf Байтовый буфер используемый для чтения данных из пакета.
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        message = ByteBufUtils.readUTF8String(buf);
        number = buf.readInt();
    }

    /**
     * Записывает данные пакета в {@link ByteBuf} перед отправкой.
     *
     * @see <a href="https://netty.io/4.0/api/io/netty/buffer/ByteBuf.html">ByteBuf</a>
     * @param buf Байтовый буфер используемый для записи данных в пакет.
     */
    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, message);
        buf.writeInt(number);
    }

    /**
     * Этот класс отвечает за обработку входящих пакетов
     */
    public static class Handler implements IMessageHandler<ServerMessagePacket, IMessage> {

        /**
         * Данный метод вызывается для обработки входящих данных из {@code message} пакета.
         *
         * @param packet Полученный пакет
         * @param ctx Контекст обработки пакетов. Позволяющий получить текущую сторону на которой было получено сообщение,
         * а также обработчиков(клиент и сервер)
         * @return Возвращает пакет для ответа клиенту(ответ с клиента на сервер в данном случае не будет работать!)
         */
        @Override
        public IMessage onMessage(ServerMessagePacket packet, MessageContext ctx) {
            String message = packet.message;
            int number = packet.number;
            // Получаем игрока, который прислал нам пакет. Это единственный параметр, которому мы можем полностью доверять.
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;

            // Отправляем сообщение игроку
            player.addChatMessage(new ChatComponentText("Вывожу текст \"" + message + "\" с числом \"" + number + "\""));
            return null;
        }
    }
}