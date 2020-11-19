package vibrantjourneys.worldgen.feature;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.storage.loot.LootTableList;
import vibrantjourneys.init.PVJBlocks;
import vibrantjourneys.util.EnumStoneType;
import vibrantjourneys.util.PVJConfig;

public class WorldGenPVJDungeon extends WorldGenerator
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int j = rand.nextInt(2) + 2;
        int k = -j - 1;
        int l = j + 1;
        int k1 = rand.nextInt(2) + 2;
        int l1 = -k1 - 1;
        int i2 = k1 + 1;
        int j2 = 0;

        for (int k2 = k; k2 <= l; ++k2)
        {
            for (int l2 = -1; l2 <= 4; ++l2)
            {
                for (int i3 = l1; i3 <= i2; ++i3)
                {
                    BlockPos blockpos = pos.add(k2, l2, i3);
                    Material material = world.getBlockState(blockpos).getMaterial();
                    boolean flag = material.isSolid();

                    if (l2 == -1 && !flag)
                    {
                        return false;
                    }

                    if (l2 == 4 && !flag)
                    {
                        return false;
                    }

                    if ((k2 == k || k2 == l || i3 == l1 || i3 == i2) && l2 == 0 && world.isAirBlock(blockpos) && world.isAirBlock(blockpos.up()))
                    {
                        ++j2;
                    }
                }
            }
        }

        if (j2 >= 1 && j2 <= 5)
        {
            for (int k3 = k; k3 <= l; ++k3)
            {
                for (int i4 = 3; i4 >= -1; --i4)
                {
                    for (int k4 = l1; k4 <= i2; ++k4)
                    {
                        BlockPos blockpos1 = pos.add(k3, i4, k4);

                        if (k3 != k && i4 != -1 && k4 != l1 && k3 != l && i4 != 4 && k4 != i2)
                        {
                            if (world.getBlockState(blockpos1).getBlock() != Blocks.CHEST)
                            {
                                world.setBlockToAir(blockpos1);
                            }
                        }
                        else if (blockpos1.getY() >= 0 && !world.getBlockState(blockpos1.down()).getMaterial().isSolid())
                        {
                            world.setBlockToAir(blockpos1);
                        }
                        else if (world.getBlockState(blockpos1).getMaterial().isSolid() && world.getBlockState(blockpos1).getBlock() != Blocks.CHEST)
                        {
                            if (i4 == -1 && rand.nextInt(4) != 0)
                            {
                                world.setBlockState(blockpos1, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
                            }
                            else
                            {
                            	if(rand.nextBoolean())
                            		world.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState(), 2);
                            	else
                            	{
                            		if(PVJConfig.master.enableStoneTypeBlocks)
                            			world.setBlockState(blockpos1, PVJBlocks.STONES.get(EnumStoneType.COBBLESTONE_BRICK.getID()).getDefaultState(), 2);
                            		else
                            			world.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState(), 2);
                            	}

                            }
                            if(world.isAirBlock(blockpos1.up()) && rand.nextInt(6) == 0)
                            {
                            	world.setBlockState(blockpos1.up(), PVJBlocks.bones.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            for (int l3 = 0; l3 < 2; ++l3)
            {
                for (int j4 = 0; j4 < 3; ++j4)
                {
                    int l4 = pos.getX() + rand.nextInt(j * 2 + 1) - j;
                    int i5 = pos.getY();
                    int j5 = pos.getZ() + rand.nextInt(k1 * 2 + 1) - k1;
                    BlockPos blockpos2 = new BlockPos(l4, i5, j5);

                    if (world.isAirBlock(blockpos2))
                    {
                        int j3 = 0;

                        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                        {
                            if (world.getBlockState(blockpos2.offset(enumfacing)).getMaterial().isSolid())
                            {
                                ++j3;
                            }
                        }

                        if (j3 == 1)
                        {
                            world.setBlockState(blockpos2, Blocks.CHEST.correctFacing(world, blockpos2, Blocks.CHEST.getDefaultState()), 2);
                            TileEntity tileentity1 = world.getTileEntity(blockpos2);

                            if (tileentity1 instanceof TileEntityChest)
                            {
                                ((TileEntityChest)tileentity1).setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());
                            }

                            break;
                        }
                    }
                }
            }

            world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner)
            {
                ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityId(this.pickMobSpawner(rand));
            }
            else
            {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", Integer.valueOf(pos.getX()), Integer.valueOf(pos.getY()), Integer.valueOf(pos.getZ()));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Randomly decides which spawner to use in a dungeon
     */
    private ResourceLocation pickMobSpawner(Random rand)
    {
        return net.minecraftforge.common.DungeonHooks.getRandomDungeonMob(rand);
    }
}