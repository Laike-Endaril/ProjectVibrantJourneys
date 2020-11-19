package vibrantjourneys.entities.neutral;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import vibrantjourneys.entities.ai.EntityAIAvoidLight;
import vibrantjourneys.init.PVJSounds;
import vibrantjourneys.util.PVJLootTableList;

public class EntityGhost extends EntityMob
{
    private boolean isFading;

    public EntityGhost(World world)
    {
        super(world);
        isFading = false;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidLight(this, 1.0D));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    public boolean getIsFading()
    {
        return isFading;
    }

    //poof!
    public void poof()
    {
        this.spawnExplosionParticle();
        this.world.removeEntity(this);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote)
        {
            BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
            int i = this.world.getLightFromNeighbors(blockpos);

            if (!this.getIsFading())
            {
                if (i > 7)
                {
                    if (this.getRNG().nextInt(300) == 0)
                        isFading = true;
                }
            }
            if (getIsFading())
            {
                //stop fading process when ghost returns to the dark
                if (i <= 7)
                    isFading = false;
                else
                {
                    this.tasks.removeTask(new EntityAIHurtByTarget(this, false, new Class[0]));
                    if (this.getRNG().nextInt(300) == 0)
                        poof();
                }
            }
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return PVJLootTableList.GHOST;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return PVJSounds.GHOST_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return PVJSounds.GHOST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return PVJSounds.GHOST_DEATH;
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (this.world.provider.getDimensionType() != DimensionType.OVERWORLD)
            return false;
        return super.getCanSpawnHere();
    }
}
