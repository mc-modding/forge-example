package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.handler.packet.ServerMessagePacket;

public class FMLEventListener {

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Если игрока зовут "PlayerName", то отправляем ему сообщение с приветствием
        if (event.player.getCommandSenderName().equals("PlayerName")) {
            event.player.addChatMessage(new ChatComponentText(String.format("Привет, %s!", event.player.getCommandSenderName())));
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
            CommonProxy.getNetwork().sendToServer(new ServerMessagePacket("Привет от SimpleNetworkWrapper!", 1337));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
            FMLProxyPacket packet = PacketHandler.makePacket(0, buf -> {
                ByteBufUtils.writeUTF8String(buf, "Привет от FMLEventChannel!");
                buf.writeInt(1234);
            });

            CommonProxy.getPacketHandler().channel.sendToServer(packet);
        }
    }
}