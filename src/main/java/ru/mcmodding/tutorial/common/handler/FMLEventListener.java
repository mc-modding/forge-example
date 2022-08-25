package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;
import ru.mcmodding.tutorial.McModding;
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
        if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
            McModding.NETWORK.sendToServer(new ServerMessagePacket("Привет мир!", 1337));
        }
    }
}