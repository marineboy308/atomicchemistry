package accecore.atomicchemistry.world.gen;

import java.util.Random;

import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.objects.blocks.BlockOreBase;
import accecore.atomicchemistry.util.handlers.EnumHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator{

	private WorldGenerator ore_overworld_copper, ore_overworld_aluminium;
	
	public WorldGenCustomOres() {
		ore_overworld_copper = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOreBase.VARIANT, EnumHandler.EnumTypeOre.COPPER), 8, BlockMatcher.forBlock(Blocks.STONE));
		
		ore_overworld_aluminium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOreBase.VARIANT, EnumHandler.EnumTypeOre.ALUMINIUM), 2, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1: //Nether
			
			
			break;
		case 0: //Overworld
			runGenerator(ore_overworld_copper, world, random, chunkX, chunkZ, 20, 16, 72);
			runGenerator(ore_overworld_aluminium, world, random, chunkX, chunkZ, 15, 5, 56);
			
			break;
		case 1: //End
			
			
			break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, int minY, int maxY) {
		if (minY > maxY || minY < 0 || maxY > 256) throw new IllegalArgumentException("Attempted to spawn ore out of bounds.");
		
		int Ydif = maxY - minY + 1;
		for (int i = 0; i < chance; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = minY + random.nextInt(Ydif);
			int z = chunkZ * 16 + random.nextInt(16);
			
			gen.generate(world, random, new BlockPos(x,y,z));
		}
	}

}
