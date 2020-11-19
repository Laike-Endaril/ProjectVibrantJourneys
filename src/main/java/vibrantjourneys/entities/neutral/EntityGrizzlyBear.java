package vibrantjourneys.entities.neutral;

import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vibrantjourneys.util.PVJLootTableList;

import javax.annotation.Nullable;

public class EntityGrizzlyBear extends EntityPolarBear
{
    public EntityGrizzlyBear(World worldIn)
    {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return PVJLootTableList.GRIZZLY_BEAR;
    }
}
