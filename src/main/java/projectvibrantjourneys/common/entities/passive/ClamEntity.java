package projectvibrantjourneys.common.entities.passive;

import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClamEntity extends WaterMobEntity {
	
	public ClamEntity(EntityType<? extends ClamEntity> type, World world) {
		super(type, world);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
	}
	
	public static boolean canSpawn(EntityType<ClamEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
		return pos.getY() > 60;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.world.isRemote) {
			if(this.rand.nextFloat() < 0.1F) {
				this.world.addParticle(ParticleTypes.BUBBLE, this.func_226277_ct_(), this.func_226279_cv_(), this.func_226281_cx_(), 0, 0, 0);
			}
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double distance) {
		return true;
	}
	
	@Override
	protected int getExperiencePoints(PlayerEntity player) {
		return 1;
	}
}
