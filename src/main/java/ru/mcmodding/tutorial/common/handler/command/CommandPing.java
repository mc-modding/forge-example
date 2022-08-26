package ru.mcmodding.tutorial.common.handler.command;

import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

/**
 * Команда "ping".
 * Для удобства мы наследуемся от класса {@link CommandBase} чтобы иметь возможность
 * пользоваться его вспомогательными методами, хотя для создания команды достаточно реализовать
 * интерфейс {@link ICommand}
 */
public class CommandPing extends CommandBase {

    @Override
    public String getCommandName() {
        return "ping";
    }

    /**
     * Определяет требуемый уровень доступа для использования этой команды.
     * @return Значение уровня доступа [0-4]
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Выводит краткую справку по использованию команды вида: {@code /command <args...>}
     * @param sender Кем была вызвана команда
     * @return Ключ локализации
     */
    @Nullable
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    /**
     * Обработчик вызова команды
     * @param sender Отправитель команды (игрок, консоль, командный блок и др.)
     * @param args Аргументы команды (то что было перечислено через пробелы после названия команды)
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        ChatComponentText messageFirst = new ChatComponentText("Pong!");
        messageFirst.getChatStyle().setColor(EnumChatFormatting.GREEN);

        // Передаём ключ локализации. Строка будет переведена на стороне на выбранный у него язык. "MC Modding" является подстановочной строкой и переведено не будет.
        ChatComponentTranslation messageSecond = new ChatComponentTranslation("mcmodding.message.hello", "MC Modding");
        messageSecond.getChatStyle().setColor(EnumChatFormatting.YELLOW);

        sender.addChatMessage(messageFirst);
        sender.addChatMessage(messageSecond);
    }
}
