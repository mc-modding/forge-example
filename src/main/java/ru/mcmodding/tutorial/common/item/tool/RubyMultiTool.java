package ru.mcmodding.tutorial.common.item.tool;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

import java.util.Set;

public class RubyMultiTool extends ItemTool {
    public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet();

    public RubyMultiTool() {
        super(5F, ModItems.RUBY_TOOL_MATERIAL, HARVEST_BLOCKS);
        setUnlocalizedName("ruby_multi_tool");
        setTextureName(McModding.MOD_ID + ":ruby_multi_tool");
        setCreativeTab(ModTab.INSTANCE);

        final int harvestLvl = ModItems.RUBY_TOOL_MATERIAL.getHarvestLevel();
        setHarvestLevel("axe", harvestLvl);
        setHarvestLevel("pickaxe", harvestLvl);
        setHarvestLevel("shovel", harvestLvl);
    }

    @Override
    public boolean onItemUse(ItemStack heldStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (player.canPlayerEdit(posX, posY, posZ, side, heldStack)) {
            final UseHoeEvent event = new UseHoeEvent(player, heldStack, world, posX, posY, posZ);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return false;
            }

            if (event.getResult() == Event.Result.ALLOW) {
                heldStack.damageItem(1, player);
                return true;
            }

            final Block blockAtPos = world.getBlock(posX, posY, posZ);

            if (side != 0 && world.getBlock(posX, posY + 1, posZ).isAir(world, posX, posY + 1, posZ) && (blockAtPos == Blocks.grass || blockAtPos == Blocks.dirt)) {
                final Block farmland = Blocks.farmland;
                world.playSoundEffect((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F, farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

                if (!world.isRemote) {
                    world.setBlock(posX, posY, posZ, farmland);
                    heldStack.damageItem(1, player);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean func_150897_b(Block block) {
        return true;
    }

    static {
        HARVEST_BLOCKS.add(Blocks.obsidian);
        HARVEST_BLOCKS.add(Blocks.emerald_ore);
        HARVEST_BLOCKS.add(Blocks.emerald_block);

        HARVEST_BLOCKS.addAll(RubyAxe.HARVEST_BLOCKS);
        HARVEST_BLOCKS.addAll(RubyPickaxe.HARVEST_BLOCKS);
        HARVEST_BLOCKS.addAll(RubySpade.HARVEST_BLOCKS);
    }
}