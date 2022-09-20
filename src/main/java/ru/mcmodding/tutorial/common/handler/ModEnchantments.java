package ru.mcmodding.tutorial.common.handler;

import net.minecraft.enchantment.Enchantment;
import ru.mcmodding.tutorial.common.enchantment.PoisonBladeEnchantment;

public class ModEnchantments {
    public static Enchantment poisonBlade;

    public static void register() {
        poisonBlade = new PoisonBladeEnchantment();
    }
}