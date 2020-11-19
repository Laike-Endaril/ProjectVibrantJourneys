package vibrantjourneys.entities.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import vibrantjourneys.util.PVJLootTableList;

public class EntitySkeletalKnight extends AbstractSkeleton
{
    public EntitySkeletalKnight(World world)
    {
        super(world);
    }

    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.235D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityCreeper)
        {
            EntityCreeper entitycreeper = (EntityCreeper) cause.getTrueSource();

            if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop())
            {
                entitycreeper.incrementDroppedSkulls();
                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
            }
        }
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return PVJLootTableList.SKELETAL_KNIGHT;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (this.world.provider.getDimensionType() != DimensionType.OVERWORLD)
            return false;
        return super.getCanSpawnHere();
    }

    @Override
    public void setCombatTask()
    {

    }
}
