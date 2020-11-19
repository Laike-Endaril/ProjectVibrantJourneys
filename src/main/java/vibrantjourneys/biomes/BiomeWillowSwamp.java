package vibrantjourneys.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vibrantjourneys.util.PVJConfig;
import vibrantjourneys.worldgen.feature.WorldGenMangroveTree;
import vibrantjourneys.worldgen.feature.WorldGenWillowTree;

import java.util.Random;

public class BiomeWillowSwamp extends BiomeSwamp
{
    public BiomeWillowSwamp(BiomeProperties properties)
    {
        super(properties);

        this.decorator.treesPerChunk = PVJConfig.worldgen.willowDensity;
        this.decorator.grassPerChunk = 7;
        this.decorator.reedsPerChunk = 13;
        this.decorator.mushroomsPerChunk = 9;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextBoolean() ? new WorldGenWillowTree() : new WorldGenMangroveTree();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() * 0.0225D, (double) pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 0x5B9F27 : 0x70D325;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x70D325;
    }
}
