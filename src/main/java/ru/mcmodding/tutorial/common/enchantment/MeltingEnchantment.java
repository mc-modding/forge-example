package ru.mcmodding.tutorial.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class MeltingEnchantment extends Enchantment {
    public MeltingEnchantment() {
        super(71, 4, EnumEnchantmentType.digger);
        setName("melting");
        addToBookList(this);
    }
}