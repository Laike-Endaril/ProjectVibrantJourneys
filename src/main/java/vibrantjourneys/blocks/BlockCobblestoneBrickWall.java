package vibrantjourneys.blocks;

import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import vibrantjourneys.init.PVJBlocks;
import vibrantjourneys.util.EnumStoneType;
import vibrantjourneys.util.IModelAllValidBlockstates;

public class BlockCobblestoneBrickWall extends BlockWall implements IModelAllValidBlockstates
{
    public BlockCobblestoneBrickWall()
    {
        super(PVJBlocks.STONES.get(EnumStoneType.COBBLESTONE_BRICK.getID()));
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }
}
