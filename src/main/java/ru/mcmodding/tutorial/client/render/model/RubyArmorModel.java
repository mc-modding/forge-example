package ru.mcmodding.tutorial.client.render.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class RubyArmorModel extends ModelBiped {

    private final ModelRenderer helmet;
    private final ModelRenderer plate;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightBoot;
    private final ModelRenderer leftBoot;

    private static RubyArmorModel instance;

    public RubyArmorModel() {
        textureWidth = 64;
        textureHeight = 32;

        helmet = new ModelRenderer(this);
        helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        helmet.cubeList.add(new ModelBox(helmet, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 1.0F));

        plate = new ModelRenderer(this);
        plate.setRotationPoint(0.0F, 0.0F, 0.0F);
        plate.cubeList.add(new ModelBox(plate, 16, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 1.01F));

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        rightArm.cubeList.add(new ModelBox(rightArm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 12, 4, 1.0F));

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        leftArm.mirror = true;
        leftArm.cubeList.add(new ModelBox(leftArm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 12, 4, 1.0F));

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        rightLeg.cubeList.add(new ModelBox(rightLeg, 0, 16, -2.0F, 1.0F, -2.0F, 4, 7, 4, 1.0F));

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        leftLeg.mirror = true;
        leftLeg.cubeList.add(new ModelBox(leftLeg, 0, 16, -2.0F, 1.0F, -2.0F, 4, 7, 4, 1.0F));

        rightBoot = new ModelRenderer(this);
        rightBoot.setRotationPoint(-1.9F, 12.0F, 0.0F);
        rightBoot.cubeList.add(new ModelBox(rightBoot, 0, 16, -2.0F, 9.0F, -2.0F, 4, 3, 4, 1.0F));

        leftBoot = new ModelRenderer(this);
        leftBoot.setRotationPoint(1.9F, 12.0F, 0.0F);
        leftBoot.mirror = true;
        leftBoot.cubeList.add(new ModelBox(leftBoot, 0, 16, -2.0F, 9.0F, -2.0F, 4, 3, 4, 1.0F));
    }

    /**
     * Отрисовка модели
     *
     * @param entity         Сущность на которой будет отрисована модель
     * @param limbSwingSpeed Скорость движения конечностями(движение ног и рук)
     * @param limbSwing      Движение конечностями
     * @param lifeTime       Время жизни сущности на которой отрисовывается модель
     * @param yaw            Поворот головы
     * @param pitch          Поворот тела
     * @param scaleFactor    Множитель размера
     */
    @Override
    public void render(Entity entity, float limbSwingSpeed, float limbSwing, float lifeTime, float yaw, float pitch, float scaleFactor) {
        setRotationAngles(limbSwingSpeed, limbSwing, lifeTime, yaw, pitch, scaleFactor, entity);

        helmet.render(scaleFactor);

        plate.render(scaleFactor);

        rightArm.render(scaleFactor);
        leftArm.render(scaleFactor);

        rightLeg.render(scaleFactor);
        leftLeg.render(scaleFactor);

        rightBoot.render(scaleFactor);
        leftBoot.render(scaleFactor);
    }

    @Override
    public void setRotationAngles(float limbSwingSpeed, float limbSwing, float lifeTime, float yaw, float pitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwingSpeed, limbSwing, lifeTime, yaw, pitch, scaleFactor, entity);
        setRotation(helmet, bipedHead);
        setRotation(plate, bipedBody);

        setRotation(rightArm, bipedRightArm);
        setRotation(leftArm, bipedLeftArm);

        setRotation(rightLeg, bipedRightLeg);
        setRotation(leftLeg, bipedLeftLeg);

        setRotation(rightBoot, bipedRightLeg);
        setRotation(leftBoot, bipedLeftLeg);
    }

    /**
     * Устанавливает состояние для определённых моделей.
     *
     * @param entity     Сущность на которой надета броня.
     * @param armorStack Стэк элемента брони который в данный момент надет на сущность.
     * @param armorSlot  Слот в котором находится броня.
     * @return возвращает самого себя.
     */
    public RubyArmorModel setStates(EntityLivingBase entity, ItemStack armorStack, int armorSlot) {
        // Отображает элемент головы, если текущий элемент брони находится в 0 слоте
        helmet.showModel = armorSlot == 0;

        // Отображает элемент правой, левой рук и тела, если текущий элемент брони находится в 1 слоте
        rightArm.showModel = leftArm.showModel = plate.showModel = armorSlot == 1;
        // Отображает элемент правой и левой ноги, если текущий элемент брони находится в 2 слоте
        rightLeg.showModel = leftLeg.showModel = armorSlot == 2;
        // Отображает элемент правого и левого ботинка, если текущий элемент брони находится в 3 слоте
        rightBoot.showModel = leftBoot.showModel = armorSlot == 3;

        // Сбрасываем значение удерживаемого предмета, а также прицеливания из лука
        heldItemRight = 0;
        aimedBow = false;

        final ItemStack heldStack = entity.getHeldItem();
        // Если в руке имеется предмет, то переключаем общее состояние "удерживания" на 1(угол поворота руки будет домножен на 1)
        heldItemRight = heldStack != null ? 1 : 0;

        // Если в руке есть предмет и он используется, то задаются определённые состояния(см. ниже)
        if (heldStack != null && entity instanceof EntityPlayer && ((EntityPlayer) entity).getItemInUseCount() > 0) {
            // Текущее состояние предмета с которым мы взаимодействуем
            final EnumAction action = heldStack.getItemUseAction();

            // Состояние "защита", пример: меч
            if (action == EnumAction.block) {
                heldItemRight = 3;// Угол поворота руки будет домножен на 3
            }
            // Состояние "прицеливание", пример: лук
            else if (action == EnumAction.bow) {
                aimedBow = true;
            }
        }
        // Сущность крадётся(присела)
        isSneak = entity.isSneaking();
        return this;
    }

    /**
     * Задаёт родительские повороты модели
     *
     * @param child  Модель которой будут установлены повороты
     * @param parent Модель от которой берутся повороты
     */
    private void setRotation(ModelRenderer child, ModelRenderer parent) {
        child.rotateAngleX = parent.rotateAngleX;
        child.rotateAngleY = parent.rotateAngleY;
        child.rotateAngleZ = parent.rotateAngleZ;

        child.rotationPointX = parent.rotationPointX;
        child.rotationPointY = parent.rotationPointY;
        child.rotationPointZ = parent.rotationPointZ;
    }

    public static RubyArmorModel getInstance() {
        if (instance == null)
            instance = new RubyArmorModel();
        return instance;
    }
}