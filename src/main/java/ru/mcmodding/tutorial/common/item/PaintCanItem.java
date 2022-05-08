package ru.mcmodding.tutorial.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ru.mcmodding.tutorial.McModding;

import java.util.List;

public class PaintCanItem extends Item {
    @SideOnly(Side.CLIENT)
    private IIcon colorMask;

    public PaintCanItem() {
        setUnlocalizedName("paint_can");
        setTextureName(McModding.MOD_ID + ":paint_can");
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int meta, int renderPass) {
        return renderPass == 1 ? colorMask : super.getIconFromDamageForRenderPass(meta, renderPass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        super.registerIcons(register);
        colorMask = register.registerIcon(McModding.MOD_ID + ":paint_can_color");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return renderPass == 0 ? 16777215 : ItemDye.field_150922_c[stack.getItemDamage() % ItemDye.field_150922_c.length];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List items) {
        for (int meta = 0, size = ItemDye.field_150922_c.length; meta < size; meta++) {
            items.add(new ItemStack(item, 1, meta));
        }
    }
}