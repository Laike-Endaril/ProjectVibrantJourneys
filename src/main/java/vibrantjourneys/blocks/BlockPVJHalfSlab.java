package vibrantjourneys.blocks;

import net.minecraft.block.state.IBlockState;

public class BlockPVJHalfSlab extends BlockPVJSlab
{
	public BlockPVJHalfSlab(IBlockState state)
	{
		super(state);
	}
	
	@Override
	public boolean isDouble()
	{
		return false;
	}
}
