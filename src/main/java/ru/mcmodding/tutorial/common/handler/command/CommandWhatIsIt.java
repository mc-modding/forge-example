package ru.mcmodding.tutorial.common.handler.command;

import java.util.Map;
import javax.annotation.Nullable;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;

/**
 * Команда описывающая на что смотрит игрок.
 * Это пример реализации обработки команд на стороне клиента.
 */
@SideOnly(Side.CLIENT)
public class CommandWhatIsIt extends CommandBase {

    private final Minecraft mc;
    private final Map<Class<? extends TileEntity>, String> tileClassToNameMap;

    public CommandWhatIsIt() {
        mc = Minecraft.getMinecraft();
        tileClassToNameMap = ReflectionHelper.getPrivateValue(TileEntity.class, null, "field_145853_j", "classToNameMap");
    }

    @Override
    public String getCommandName() {
        return "whatisit";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Nullable
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    /**
     * Обработчик вызова команды
     *
     * @param sender Отправитель команды (игрок, консоль, командный блок и др.)
     * @param args Аргументы команды (то что было перечислено через пробелы после названия команды)
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        WorldClient world = mc.theWorld;
        MovingObjectPosition mop = mc.objectMouseOver;

        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            TileEntity tile = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);

            sender.addChatMessage(new ChatComponentText("Блок, на который вы смотрите:"));
            sender.addChatMessage(new ChatComponentText("- Class: " + block.getClass().getName()));
            sender.addChatMessage(new ChatComponentText("- ID: " + Block.blockRegistry.getNameForObject(block)));
            sender.addChatMessage(new ChatComponentText("- Legacy ID: " + Block.blockRegistry.getIDForObject(block)));
            sender.addChatMessage(new ChatComponentText("- Metadata: " + world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ)));

            if (tile == null) {
                sender.addChatMessage(new ChatComponentText("- Не имеет TileEntity"));
            } else {
                sender.addChatMessage(new ChatComponentText("- TE Class: " + tile.getClass().getName()));
                sender.addChatMessage(new ChatComponentText("- TE ID: " + tileClassToNameMap.get(tile.getClass())));
                sender.addChatMessage(new ChatComponentText("- TE canUpdate: " + tile.canUpdate()));
            }

        } else if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            Entity entity = mop.entityHit;

            sender.addChatMessage(new ChatComponentText("Объект, на который вы смотрите:"));
            sender.addChatMessage(new ChatComponentText("- Имя: " + entity.getCommandSenderName()));
            sender.addChatMessage(new ChatComponentText("- Class: " + entity.getClass().getName()));
            sender.addChatMessage(new ChatComponentText("- ID: " + EntityList.getEntityString(entity)));
            sender.addChatMessage(new ChatComponentText("- UUID: " + entity.getPersistentID()));

            if (entity instanceof EntityLivingBase) {
                EntityLivingBase living = (EntityLivingBase) entity;
                sender.addChatMessage(new ChatComponentText("- Здоровье: " + living.getHealth() + "/" + living.getMaxHealth()));
            }

        } else {
            ChatComponentText message = new ChatComponentText("На прицеле ничего нет!");
            message.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(message);
        }
    }
}
