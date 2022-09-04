package ru.mcmodding.tutorial.client.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.handler.PacketHandler;
import ru.mcmodding.tutorial.common.handler.packet.ServerMessagePacket;

/**
 * Обработчик клавиш мода
 */
public final class KeyHandler {

    private final KeyBinding sendPacket1 = new KeyBinding("mcmodding.key.sendPacket1", Keyboard.KEY_N, "mcmodding.key.category");
    private final KeyBinding sendPacket2 = new KeyBinding("mcmodding.key.sendPacket2", Keyboard.KEY_P, "mcmodding.key.category");

    /**
     * Регистрирует клавиши в настройках игры и обработчик событий
     */
    public void register() {
        ClientRegistry.registerKeyBinding(sendPacket1);
        ClientRegistry.registerKeyBinding(sendPacket2);

        FMLCommonHandler.instance().bus().register(this);
    }

    /**
     * Обработчик события нажатия клавиши в игре
     */
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (sendPacket1.isPressed()) {
            CommonProxy.getNetwork().sendToServer(new ServerMessagePacket("Привет от SimpleNetworkWrapper!", 1337));
        }

        if (sendPacket2.isPressed()) {
            FMLProxyPacket packet = PacketHandler.makePacket(0, buf -> {
                ByteBufUtils.writeUTF8String(buf, "Привет от FMLEventChannel!");
                buf.writeInt(1234);
            });

            CommonProxy.getPacketHandler().channel.sendToServer(packet);
        }
    }
}
