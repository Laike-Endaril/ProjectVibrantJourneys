package vibrantjourneys.blocks.wood;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import vibrantjourneys.util.IModelAllValidBlockstates;

public class BlockPVJPressurePlate extends BlockPressurePlate implements IModelAllValidBlockstates
{
    public BlockPVJPressurePlate()
    {
        super(Material.WOOD, BlockPressurePlate.Sensitivity.EVERYTHING);
        this.setHardness(0.5F);
        this.setSoundType(SoundType.WOOD);
    }
}
