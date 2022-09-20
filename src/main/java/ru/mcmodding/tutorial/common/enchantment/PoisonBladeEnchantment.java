package ru.mcmodding.tutorial.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PoisonBladeEnchantment extends Enchantment {
    public PoisonBladeEnchantment() {
        super(70, 3, EnumEnchantmentType.weapon);
        setName("poison_blade");
        addToBookList(this);
    }

    /**
     * Дополнительная обработка при атаке сущности.
     *
     * @param attacker атакующий.
     * @param victim цель атакующего или его жертва.
     * @param level уровень чар
     */
    @Override
    public void func_151368_a(EntityLivingBase attacker, Entity victim, int level) {
        if (victim instanceof EntityLivingBase) {
            ((EntityLivingBase) victim).addPotionEffect(new PotionEffect(Potion.poison.id, 200 * level));
        }
    }

    /**
     * Максимальный уровень чар.
     *
     * @return Возвращает максимальный уровень для зачаривания.
     */
    @Override
    public int getMaxLevel() {
        return 5;
    }
}