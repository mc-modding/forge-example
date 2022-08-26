package ru.mcmodding.tutorial.common.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.block.RubyBlockRenderer;
import ru.mcmodding.tutorial.client.render.tile.SmelterTesr;
import ru.mcmodding.tutorial.client.render.tile.TankFluidTesr;
import ru.mcmodding.tutorial.common.block.*;
import ru.mcmodding.tutorial.common.block.fluid.JuiceFluidBlock;
import ru.mcmodding.tutorial.common.block.fluid.SteamFluidBlock;
import ru.mcmodding.tutorial.common.block.fluid.TankFluidBlock;
import ru.mcmodding.tutorial.common.item.block.ColoredStoneBlockItem;
import ru.mcmodding.tutorial.common.tile.SmelterTile;
import ru.mcmodding.tutorial.common.tile.StorageTile;
import ru.mcmodding.tutorial.common.tile.TankFluidTile;

public class ModBlocks {
    public static final ColoredStoneBlock COLORED_STONE = new ColoredStoneBlock();
    public static final FaceBlock FACE = new FaceBlock();
    public static final RubyBlock RUBY = new RubyBlock();
    public static final RubyOreBlock RUBY_ORE = new RubyOreBlock();
    public static final SmelterBlock SMELTER = new SmelterBlock();
    public static final StorageBlock STORAGE = new StorageBlock();
    public static final TankFluidBlock TANK_FLUID = new TankFluidBlock();

    public static final Fluid JUICE_FLUID = new Fluid(McModding.MOD_ID + ":juice");
    public static final Fluid STEAM_FLUID = new Fluid(McModding.MOD_ID + ":steam").setDensity(-1).setGaseous(true);

    public static JuiceFluidBlock JUICE;
    public static SteamFluidBlock STEAM;

    public static int rubyRenderId = -1;

    public static void register() {
        GameRegistry.registerBlock(COLORED_STONE, ColoredStoneBlockItem.class, "colored_stone");
        GameRegistry.registerBlock(FACE, "face");
        GameRegistry.registerBlock(RUBY, "ruby_block");
        GameRegistry.registerBlock(RUBY_ORE, "ruby_ore");
        GameRegistry.registerBlock(SMELTER, "smelter");
        GameRegistry.registerBlock(STORAGE, "storage");
        GameRegistry.registerBlock(TANK_FLUID, "tank_fluid");

        GameRegistry.registerTileEntity(SmelterTile.class, McModding.MOD_ID + ":smelter");
        GameRegistry.registerTileEntity(StorageTile.class, McModding.MOD_ID + ":storage");
        GameRegistry.registerTileEntity(TankFluidTile.class, McModding.MOD_ID + ":tank_fluid");

        // Жидкость должна быть зарегистрирована раньше, чем блок для которой она будет прикреплена!
        if (FluidRegistry.registerFluid(JUICE_FLUID)) {
            // Если конфликтов никаких нет, то регистрируем блок для жидкости, иначе ничего не делаем.
            JUICE = new JuiceFluidBlock(JUICE_FLUID);
            GameRegistry.registerBlock(JUICE, "juice");

            // Регистрировать ведро необходимо после регистрации блока жидкости и самой жидкости.
            ModItems.JUICE_BUCKET = BucketHandler.registryBucket("juice", JUICE_FLUID);
        }

        if (FluidRegistry.registerFluid(STEAM_FLUID)) {
            STEAM = new SteamFluidBlock(STEAM_FLUID);
            GameRegistry.registerBlock(STEAM, "steam");

            ModItems.STEAM_BUCKET = BucketHandler.registryBucket("steam", STEAM_FLUID);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        rubyRenderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(new RubyBlockRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(SmelterTile.class, new SmelterTesr());
        ClientRegistry.bindTileEntitySpecialRenderer(TankFluidTile.class, new TankFluidTesr());
    }
}