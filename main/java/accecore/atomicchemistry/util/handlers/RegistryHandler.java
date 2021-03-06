package accecore.atomicchemistry.util.handlers;

import accecore.atomicchemistry.init.BiomeInit;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import accecore.atomicchemistry.world.gen.WorldGenCustomOres;
import accecore.atomicchemistry.world.gen.WorldGenCustomTrees;
import accecore.atomicchemistry.world.types.WorldTypeAtomicLevel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		
		for(Item item : ItemInit.ITEMS) {
			
			if(item instanceof IHasModel) {
				
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS) {
			
			if(block instanceof IHasModel) {
				
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void perInitRegistries() {
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomTrees(), 0);
		
		BiomeInit.registerBiomes();
	}
	
	public static void postInitRegistries() {
		WorldType ATOMICLEVEL = new WorldTypeAtomicLevel();
	}
}
