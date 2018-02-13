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

public class BiomeAbyss extends Biome {

	public BiomeAbyss() {
		super(new BiomeProperties("Collapsing Atomic Land").setBaseHeight(0.0F).setHeightVariation(0.0F).setRainfall(0.0F).setRainDisabled().setTemperature(0.0F).setWaterColor(0));
		
		topBlock = BlockInit.ABYSS.getDefaultState();
		fillerBlock = BlockInit.ABYSS.getDefaultState();
		
		this.decorator.decorating = true;

		this.decorator.treesPerChunk = 0;
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}
}
