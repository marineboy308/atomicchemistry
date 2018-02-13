package accecore.atomicchemistry.init;

import accecore.atomicchemistry.world.biomes.BiomeAbyss;
import accecore.atomicchemistry.world.biomes.BiomeAtomic;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {

	public static final Biome ATOMIC = new BiomeAtomic();
	public static final Biome ABYSS = new BiomeAbyss();
	
	public static void registerBiomes() {
		initBiome(ATOMIC, "Collapsing Atomic Land", BiomeType.WARM, Type.DEAD, Type.DENSE, Type.HOT, Type.SPARSE, Type.WASTELAND, Type.PLAINS, Type.SPOOKY, Type.END);
		initBiome(ABYSS, "Abyss", BiomeType.COOL, Type.DEAD, Type.DENSE, Type.WASTELAND, Type.PLAINS, Type.SPOOKY, Type.END);
	}
	
	public static Biome initBiome(Biome biome, String name, BiomeType biometype, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.println("Biome " + name + " Has Been Registered.");
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biometype, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("Biome " + name + " Has Been Added.");
		return biome;
	}
}
