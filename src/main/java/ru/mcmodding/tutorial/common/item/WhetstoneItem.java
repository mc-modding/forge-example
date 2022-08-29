package ru.mcmodding.tutorial.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class WhetstoneItem extends Item {
    private final String name;

    @SideOnly(Side.CLIENT)
    private IIcon[] damagedIcons;

    public WhetstoneItem(String name, int maxDamage) {
        this.name = name;
        setMaxStackSize(1);
        setUnlocalizedName(name);
        setTextureName(String.format("%s:%s/default", McModding.MOD_ID, name));
        setCreativeTab(ModTab.INSTANCE);
        setMaxDamage(maxDamage);
        setNoRepair();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase attacker) {
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        return false;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        ItemStack stackCopy = stack.copy();
        stackCopy.setItemDamage(stackCopy.getItemDamage() + 1);
        return stackCopy.getItemDamage() >= stackCopy.getMaxDamage() ? null : stackCopy;
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        super.registerIcons(register);
        damagedIcons = new IIcon[5];
        for (int count = 0; count < 5; count++) {
            damagedIcons[count] = register.registerIcon(String.format("%s:%s/damage_%d", McModding.MOD_ID, name, count));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int itemDamage) {
        int segment = MathHelper.floor_float(((float) itemDamage / getMaxDamage()) * 10) / 2;
        return itemDamage == 0 ? itemIcon : damagedIcons[segment];
    }
}