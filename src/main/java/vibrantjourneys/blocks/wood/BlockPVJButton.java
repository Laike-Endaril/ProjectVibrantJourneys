package vibrantjourneys.blocks.wood;

import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;
import vibrantjourneys.util.IModelAllValidBlockstates;

public class BlockPVJButton extends BlockButtonWood implements IModelAllValidBlockstates
{
    public BlockPVJButton()
    {
        super();
        this.setHardness(0.5F);
        this.setSoundType(SoundType.WOOD);
    }
}
