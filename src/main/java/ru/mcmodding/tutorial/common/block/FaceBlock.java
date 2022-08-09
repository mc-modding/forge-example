package ru.mcmodding.tutorial.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class FaceBlock extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon faceIcon;

    public FaceBlock() {
        super(Material.rock);
        setBlockName("face");
        setBlockTextureName("stone");
        setCreativeTab(ModTab.INSTANCE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta == 2 && side == 2) return faceIcon;
        if (meta == 3 && side == 5) return faceIcon;
        if (meta == 0 && side == 3) return faceIcon;
        if (meta == 1 && side == 4) return faceIcon;
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        faceIcon = register.registerIcon(McModding.MOD_ID + ":face");
    }

    /**
     * Данный метод вызывается после установки блока в мире.
     *
     * @param world     мир в котором устанавливается блок.
     * @param x         позиция на которой был установлен блок по X координате.
     * @param y         позиция на которой был установлен блок по Y координате.
     * @param z         позиция на которой был установлен блок по Z координате.
     * @param placer    сущность установившая блок в мире.
     * @param stack     предмет с помощью которого был установлен блок.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int direction = MathHelper.floor_double(((placer.rotationYaw * 4.0) / 360.0) + 2.5) & 3;
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }
}