package vibrantjourneys.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import vibrantjourneys.blocks.BlockRockFormation;

public class WorldGenIcicleUp implements IWorldGenerator
{
	private Block block;
	private int minY, maxY;
	private int frequency;
	private Biome[] biomes;
	
	public WorldGenIcicleUp(Block block, int minY, int maxY, int frequency, Biome... biomes)
	{
		this.block = block;
		this.minY = minY;
		this.maxY = maxY;
		this.frequency = frequency;
		this.biomes = biomes;
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		Random random = new Random();
		if(world.provider.getDimensionType() != DimensionType.OVERWORLD && world.provider.getDimensionType() != DimensionType.NETHER)
			return;
		
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		
		Biome biome = world.getBiomeForCoordsBody(new BlockPos(x, 0, z));
		boolean isValidBiome = false;
		for(int i = 0; i < biomes.length; i++)
		{
			if(biome == biomes[i])
			{
				isValidBiome = true;
				break;
			}
		}
		
		if(isValidBiome)
		{
			for(int i = 0; i < frequency; i++)
			{
				int xPos = x + random.nextInt(8) - random.nextInt(8);
				int zPos = z + random.nextInt(8) - random.nextInt(8);
				int yPos = minY + random.nextInt(maxY - minY + 1);
				
				BlockPos pos = new BlockPos(xPos, yPos, zPos);
				if(world.canSeeSky(pos.up())) //caves only!
					return;
				
				Material material = world.getBlockState(pos.up()).getMaterial();

				if((material == Material.ROCK  || material == Material.PACKED_ICE) && world.isSideSolid(pos.down(), EnumFacing.UP))
				{
					for(int size = 0; size < 3; size++)
					{
						if(world.isAirBlock(pos.up(size)))
						{
							world.setBlockState(pos.up(size), block.getDefaultState().withProperty(BlockRockFormation.SIZE, size));
						}
						else
						{
							break;
						}
					}
				}
			}
		}
	}
	
	
}
