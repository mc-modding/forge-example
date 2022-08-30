package ru.mcmodding.tutorial.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.CommonProxy;
import ru.mcmodding.tutorial.common.handler.ModTab;
import ru.mcmodding.tutorial.common.tile.ChestTile;

public class ChestBlock extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon iconTop, iconSide, iconFront;

    public ChestBlock() {
        super(Material.wood);
        setCreativeTab(ModTab.INSTANCE);
        setHardness(2.5F);
        setStepSound(soundTypeWood);
        setBlockName("mcmodding.chest");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ChestTile();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        iconTop = register.registerIcon(McModding.MOD_ID + ":chest_top");
        iconSide = register.registerIcon(McModding.MOD_ID + ":chest_side");
        iconFront = register.registerIcon(McModding.MOD_ID + ":chest_front");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side >= 2 && side <= 5) {
            if (meta == side || (meta == 0 && side == 3)) return iconFront;
            return iconSide;
        }

        return iconTop;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int rotation = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = 0;

        switch (rotation) {
            case 0:
                meta = 2;
                break;
            case 1:
                meta = 5;
                break;
            case 2:
                meta = 3;
                break;
            case 3:
                meta = 4;
                break;
        }

        world.setBlockMetadataWithNotify(x, y, z, meta, 3);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof ChestTile) {
                player.openGui(McModding.instance, CommonProxy.GUI_CHEST, world, x, y, z);
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof ChestTile) {
            ChestTile chest = ((ChestTile)tile);

            for (int slot = 0; slot < chest.getSizeInventory(); slot++) {
                ItemStack stack = chest.getStackInSlot(slot);

                if (stack != null) {
                    float offsetX = world.rand.nextFloat() * 0.8F + 0.1F;
                    float offsetY = world.rand.nextFloat() * 0.8F + 0.1F;
                    float offsetZ = world.rand.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityItem = new EntityItem(world, (float)x + offsetX, (float)y + offsetY, (float)z + offsetZ, stack);
                    float range = 0.05F;
                    entityItem.motionX = (float)world.rand.nextGaussian() * range;
                    entityItem.motionY = (float)world.rand.nextGaussian() * range + 0.2F;
                    entityItem.motionZ = (float)world.rand.nextGaussian() * range;
                    world.spawnEntityInWorld(entityItem);

                    chest.setInventorySlotContents(slot, null);
                }
            }
        }

        super.breakBlock(world, x, y, z, block, meta); // Наличие этого вызова важно - удаляет тайл
    }
}
