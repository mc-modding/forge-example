package ru.mcmodding.tutorial.common.handler;

import net.minecraft.enchantment.Enchantment;
import ru.mcmodding.tutorial.common.enchantment.PoisonBladeEnchantment;
import ru.mcmodding.tutorial.common.enchantment.MeltingEnchantment;

public class ModEnchantments {
    public static Enchantment poisonBlade;
    public static Enchantment melting;

    public static void register() {
        poisonBlade = new PoisonBladeEnchantment();
        melting = new MeltingEnchantment();
    }
}