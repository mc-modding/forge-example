package ru.mcmodding.tutorial.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;
import ru.mcmodding.tutorial.common.tile.SmelterTile;

public class SmelterBlock extends BlockContainer {
    public SmelterBlock() {
        super(Material.wood);
        setBlockName("smelter");
        setBlockTextureName(McModding.MOD_ID + ":smelter");
        setCreativeTab(ModTab.INSTANCE);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile instanceof SmelterTile) {
                SmelterTile smelter = (SmelterTile) tile;
                smelter.handleInputStack(activator, activator.getHeldItem());
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new SmelterTile();
    }
}