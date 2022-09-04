package ru.mcmodding.tutorial.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.mcmodding.tutorial.McModding;

public class GooseEntity extends EntityAnimal {
    public GooseEntity(World world) {
        super(world);
        setSize(0.9F, 0.9F);
        getNavigator().setAvoidsWater(true);

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIMate(this, 1.0D));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0, true));
        tasks.addTask(2, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(3, new EntityAIWander(this, 1.0D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(5, new EntityAILookIdle(this));
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    public String getLivingSound() {
        return McModding.MOD_ID + ":goose_say";
    }

    @Override
    public String getHurtSound() {
        return McModding.MOD_ID + ":goose_say";
    }

    @Override
    public String getDeathSound() {
        return McModding.MOD_ID + ":goose_death";
    }

    @Override
    public void func_145780_a(int x, int y, int z, Block block) {
        playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    @Override
    public Item getDropItem() {
        return Items.feather;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemSeeds;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return new GooseEntity(worldObj);
    }
}