package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.packet.ServerMessagePacket;

public class FMLEventListener {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
            McModding.NETWORK.sendToServer(new ServerMessagePacket("Привет мир!", 1337));
        }
    }
}