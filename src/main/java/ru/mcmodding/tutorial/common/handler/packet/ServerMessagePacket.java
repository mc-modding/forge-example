package ru.mcmodding.tutorial.common.handler.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ChatComponentText;

public class ServerMessagePacket implements IMessage, IMessageHandler<ServerMessagePacket, IMessage> {
    private String message;
    private int number;

    /**
     * Должен быть пустым, чтобы во время регистрации пакета не приходилось указывать параметры.
     */
    public ServerMessagePacket() {
    }

    /**
     * Будет использоваться для формирования пакета по параметрам.
     *
     * @param message Сообщения, которое будет выводиться на серверной стороне.
     * @param number Число, которое будет выводиться на серверной стороне.
     */
    public ServerMessagePacket(String message, int number) {
        this.message = message;
        this.number = number;
    }

    /**
     * Данный метод вызывается при чтении из байтового буфера.
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
     * Данный метод вызывается при записи в байтовый буфер.
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
     * Данный метод вызывается для обработки входящих данных из {@code message} пакета.
     *
     * @param packet Пакет с информацией. Рекомендуется использовать его для получения данных!
     * @param ctx Контекст обработки пакетов. Позволяющий получить текущую сторону на которой было получено сообщение,
     *            а также обработчиков(клиент и сервер)
     * @return Возвращает пакет для ответа клиенту(ответ с клиента на сервер в данном случае не будет работать!)
     */
    @Override
    public IMessage onMessage(ServerMessagePacket packet, MessageContext ctx) {
        String message = packet.message;
        int number = packet.number;
        // Отправляем сообщение игроку, который отправил пакет с данными.
        ctx.getServerHandler().playerEntity.addChatMessage(new ChatComponentText("Вывожу текст \"" + message + "\" с числом \"" + number + "\""));
        return null;
    }
}