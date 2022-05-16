package ru.mcmodding.tutorial.common.item.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import ru.mcmodding.tutorial.McModding;
import ru.mcmodding.tutorial.client.render.model.RubyArmorModel;
import ru.mcmodding.tutorial.common.handler.ModItems;
import ru.mcmodding.tutorial.common.handler.ModTab;

public class RubyArmor extends ItemArmor implements ISpecialArmor {

    /* Имеет ли броня модель */
    private final boolean hasModel;

    /**
     * Инициализирует объект брони без модели
     *
     * @param elementName Название элемента брони
     * @param armorType   Тип брони(0 - шлем, 1 - нагрудник, 2 - штаны, 3 - ботинки)
     */
    public RubyArmor(String elementName, int armorType) {
        this(elementName, armorType, false);
    }

    /**
     * Инициализирует объект брони
     *
     * @param elementName Название элемента брони
     * @param armorType   Тип брони(0 - шлем, 1 - нагрудник, 2 - штаны, 3 - ботинки)
     * @param hasModel    Имеет ли модель
     */
    public RubyArmor(String elementName, int armorType, boolean hasModel) {
        super(ModItems.RUBY_ARMOR_MATERIAL, 0, armorType);
        this.hasModel = hasModel;
        setUnlocalizedName("ruby_" + elementName);
        setTextureName(McModding.MOD_ID + ":ruby_" + elementName);
        setCreativeTab(ModTab.INSTANCE);
    }

    /**
     * Формирует путь к текстуре брони
     *
     * @param stack  Стэк элемента брони который в данный момент надет на сущность
     * @param entity Сущность на которой надета броня
     * @param slot   Слот в котором находится броня
     * @param type   Подтип текстуры, может быть null или "overlay". Используется для наложения маски цвета для кожаной брони.
     * @return возвращает путь до текстуры брони
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (hasModel)
            return McModding.MOD_ID + ":textures/models/armor/ruby_armor.png";
        return String.format("%s:textures/models/armor/ruby_layer_%d.png", McModding.MOD_ID, slot == 2 ? 2 : 1);
    }

    /**
     * Обработка предметом собственного рендера брони.
     *
     * @param entity     Сущность на которой надета броня.
     * @param armorStack Стэк элемента брони который в данный момент надет на сущность.
     * @param armorSlot  Слот в котором находится броня.
     * @return Возвращает модель брони. Если Null, то будет отрисована обычная модель брони.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack armorStack, int armorSlot) {
        return !hasModel ? null : RubyArmorModel.getInstance().setStates(entity, armorStack, armorSlot);
    }

    /* ISpecialArmor implementation */

    /**
     * Извлекает модификаторы, которые будут использоваться при расчете урона по броне.
     * <p>
     * Броня с более высоким приоритетом будет получать урон, наносимый ей перед броней с более низким приоритетом.
     * Если есть несколько единиц брони с одинаковым приоритетом,
     * урон будет распределен между ними на основе коэффициента поглощения.
     *
     * @param player Сущность на которой надета броня.
     * @param armor  Стэк элемента брони который в данный момент надет на сущность.
     * @param source Источник урона, который может быть использован для изменения свойств брони в зависимости от типа или наносящего урон(сущность или игрок).
     * @param damage Общий урон, применяемый к сущности
     * @param slot   Слот в котором находится броня.
     * @return Экземпляр ArmorProperties, содержащий информацию о том, как броне наносится урон.
     */
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (slot == 1) {
            // Вернём для нагрудника свойство к двукратному поглощению
            return new ArmorProperties(1, 2.0, 1_000);
        }
        // Вернёт стандартные значения свойства брони
        return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
    }

    /**
     * Получает отображаемое кол-во брони.
     *
     * @param player Сущность на которой надета броня.
     * @param armor  Стэк элемента брони который в данный момент надет на сущность.
     * @param slot   Слот в котором находится броня.
     * @return Количество очков брони для отображения, 2 на щит.
     */
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (slot == 1) {
            return 6;// Отобразим 3 щита для слота нагрудника
        }
        return damageReduceAmount;// Кол-во брони будет отображаться в зависимости от коэффициента поглощения
    }

    /**
     * Наносит урон стэку брони. Метод отвечает за уменьшение долговечности предмета и размера стэка.
     * Если стэк исчерпан, он будет удалён автоматически из инвентаря.
     *
     * @param entity Сущность на которой надета броня.
     * @param stack  Стэк элемента брони который в данный момент надет на сущность.
     * @param source Источник урона, который может быть использован для изменения свойств брони в зависимости от типа или наносящего урон(сущность или игрок).
     * @param damage Общий урон, применяемый к броне
     * @param slot   Слот в котором находится броня.
     */
    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        // Если урон исходит от кактуса, то мы не будем ломать стэк нашей брони
        if (source == DamageSource.cactus) {
            return;
        }
        stack.damageItem(damage, entity);// Урон стэку будет нанесён как для обычной брони
    }
}