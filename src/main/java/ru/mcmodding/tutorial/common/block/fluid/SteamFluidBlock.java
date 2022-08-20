package ru.mcmodding.tutorial.common.block.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class SteamFluidBlock extends BlockFluidClassic {
    public SteamFluidBlock(Fluid fluid) {
        super(fluid, Material.water);
        fluid.setBlock(this);
        setBlockName("steam");
        setBlockTextureName(McModding.MOD_ID + ":steam_still");
        setCreativeTab(ModTab.INSTANCE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        getFluid().setIcons(blockIcon, register.registerIcon(McModding.MOD_ID + ":steam_flow"));
    }
}