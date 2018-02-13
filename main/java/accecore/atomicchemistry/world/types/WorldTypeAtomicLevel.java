package accecore.atomicchemistry.world.types;

import accecore.atomicchemistry.init.BiomeInit;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeAtomicLevel extends WorldType{

	public WorldTypeAtomicLevel() {
		super("atomic");
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(BiomeInit.ATOMIC);
	}

}
