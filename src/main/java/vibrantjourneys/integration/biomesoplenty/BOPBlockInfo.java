package vibrantjourneys.integration.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import vibrantjourneys.util.BiomeReference;

import java.util.ArrayList;

public class BOPBlockInfo
{
    private Block block;
    private int worldGenDensity;
    private ArrayList<Biome> referenceList;

    public BOPBlockInfo(Block block, int worldGenDensity, ArrayList<Biome> referenceList)
    {
        this.block = block;
        this.worldGenDensity = worldGenDensity;
        this.referenceList = referenceList;
    }

    public Block getBlock()
    {
        return block;
    }

    public int getWorldGenDensity()
    {
        return worldGenDensity;
    }

    public Biome[] getBiomes()
    {
        return BiomeReference.getBiomes(referenceList);
    }
}
