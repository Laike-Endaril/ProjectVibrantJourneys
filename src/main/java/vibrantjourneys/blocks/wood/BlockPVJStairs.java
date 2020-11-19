package vibrantjourneys.blocks.wood;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import vibrantjourneys.util.IModelAllValidBlockstates;

public class BlockPVJStairs extends BlockStairs implements IModelAllValidBlockstates
{
    public BlockPVJStairs(IBlockState modelState)
    {
        super(modelState);
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return Blocks.OAK_STAIRS.getFlammability(world, pos, face);
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return Blocks.OAK_STAIRS.getFireSpreadSpeed(world, pos, face);
    }
}
