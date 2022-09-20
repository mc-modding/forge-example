package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.util.ChatComponentText;

public class FMLEventListener {

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Если игрока зовут "PlayerName", то отправляем ему сообщение с приветствием
        if (event.player.getCommandSenderName().equals("PlayerName")) {
            event.player.addChatMessage(new ChatComponentText(String.format("Привет, %s!", event.player.getCommandSenderName())));
        }
    }
}