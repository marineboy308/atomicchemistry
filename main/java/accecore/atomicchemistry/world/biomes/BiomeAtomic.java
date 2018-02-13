package accecore.atomicchemistry.world.biomes;

import java.util.Random;

import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.objects.blocks.BlockDirts;
import accecore.atomicchemistry.objects.blocks.BlockHardenedDirts;
import accecore.atomicchemistry.objects.blocks.BlockOreBase;
import accecore.atomicchemistry.util.handlers.EnumHandler;
import accecore.atomicchemistry.world.gen.generators.WorldGenAtomTree;
import net.minecraft.command.EntitySelector;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;

public class BiomeAtomic extends Biome {
	
	protected static final WorldGenAbstractTree TREE = new WorldGenAtomTree();

	public BiomeAtomic() {
		super(new BiomeProperties("Collapsing Atomic Land").setBaseHeight(0.2F).setHeightVariation(0.5F).setRainfall(5.0F).setTemperature(3.4F).setWaterColor(5123980));
		
		topBlock = BlockInit.DIRTS.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandler.EnumTypeMaterials.ATOMIC);
		fillerBlock = BlockInit.HARDENED_DIRTS.getDefaultState().withProperty(BlockHardenedDirts.VARIANT, EnumHandler.EnumTypeMaterials.ATOMIC);
		
		this.decorator.decorating = true;
		
		this.decorator.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), 14);
		this.decorator.ironGen = new WorldGenMinable(Blocks.COAL_BLOCK.getDefaultState(), 5);
		this.decorator.goldGen = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOreBase.VARIANT, EnumHandler.EnumTypeOre.ALUMINIUM), 6);
		
		this.decorator.treesPerChunk = 1;
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWitherSkeleton.class, 1, 1, 3));
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySkeleton.class, 2, 2, 5));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE;
	}
}
