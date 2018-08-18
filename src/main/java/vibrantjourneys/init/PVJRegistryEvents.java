package vibrantjourneys.init;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import vibrantjourneys.ProjectVibrantJourneys;
import vibrantjourneys.blocks.BlockMysticalGrill;
import vibrantjourneys.blocks.BlockPVJDoor;
import vibrantjourneys.blocks.BlockPVJFenceGate;
import vibrantjourneys.integration.biomesoplenty.PVJRenderingHandlerBOP;
import vibrantjourneys.util.IPropertyHelper;
import vibrantjourneys.util.Reference;

public class PVJRegistryEvents
{
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		for(Block block : PVJBlocks.BLOCKS)
		{
			registry.register(block);
		}
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		for(Item item : PVJItems.ITEMS)
		{
			registry.register(item);
		}
	}
	
	@SubscribeEvent
	public void registerItemModels(ModelRegistryEvent event)
	{
		for(Block block : PVJBlocks.BLOCKS)
		{
			ProjectVibrantJourneys.proxy.registerItemRenderer(Item.getItemFromBlock(block));
			
			if(block instanceof IPropertyHelper)
			{
				IPropertyHelper PVJBlock = (IPropertyHelper)block;
				ImmutableList<IBlockState> properties = PVJBlock.getProperties();
				for(IBlockState state : properties)
				{
					int meta = block.getMetaFromState(state);
					ModelResourceLocation resource = new ModelResourceLocation(block.getRegistryName(), "inventory");
					ProjectVibrantJourneys.proxy.registerItemVariantRenderer(Item.getItemFromBlock(block), meta, resource);
				}
			}
		}
		
		for(Item item : PVJItems.ITEMS)
		{
			ProjectVibrantJourneys.proxy.registerItemRenderer(item);
		}
		
		//these block properties are ignored in the blockstates jsons
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.willow_door), BlockPVJDoor.POWERED);
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.mangrove_door), BlockPVJDoor.POWERED);
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.palm_door), BlockPVJDoor.POWERED);
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.redwood_door), BlockPVJDoor.POWERED);
		
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.willow_fence_gate), BlockPVJFenceGate.POWERED);
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.mangrove_fence_gate), BlockPVJFenceGate.POWERED);
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.palm_fence_gate), BlockPVJFenceGate.POWERED);
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.redwood_fence_gate), BlockPVJFenceGate.POWERED);
		
		ProjectVibrantJourneys.proxy.setIgnoredPropertiesForModel(Item.getItemFromBlock(PVJBlocks.mystical_grill), BlockMysticalGrill.IS_COOKING);
	}
	

	
	/*
	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		IForgeRegistry<Biome> registry = event.getRegistry();
		for(Biome biome : PVJBiomes.BIOMES)
		{
			registry.register(biome);
		}
	}*/
	
	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		IForgeRegistry<EntityEntry> registry = event.getRegistry();
		for(EntityEntry entity : PVJEntities.ENTITIES)
		{
			registry.register(entity);
		}
		
		ProjectVibrantJourneys.proxy.registerEntityRenderers();
	}
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> event)
	{
		IForgeRegistry<SoundEvent> registry = event.getRegistry();
		for(SoundEvent sound : PVJSounds.SOUND_EVENTS)
		{
			registry.register(sound);
		}
	}
}