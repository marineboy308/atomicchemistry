package accecore.atomicchemistry.init;

import java.util.ArrayList;
import java.util.List;

import accecore.atomicchemistry.objects.blocks.BlockBase;
import accecore.atomicchemistry.objects.blocks.BlockOreBase;
import accecore.atomicchemistry.objects.blocks.BlockTreeLeaves;
import accecore.atomicchemistry.objects.blocks.BlockTreeSaplings;
import accecore.atomicchemistry.objects.blocks.BlockWoodLogs;
import accecore.atomicchemistry.objects.blocks.BlockWoodPlanks;
import accecore.atomicchemistry.objects.blocks.customblocks.BlockAtomicScanner;
import accecore.atomicchemistry.objects.blocks.customblocks.BlockCounter;
import accecore.atomicchemistry.objects.blocks.customblocks.BlockMicroscope;
import accecore.atomicchemistry.objects.blocks.customblocks.BlockTestTubeRack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BASE = new BlockBase("block_texture_microscope", Material.BARRIER, false);
	
	public static final Block ORE_OVERWORLD = new BlockOreBase("ore_overworld", Material.ROCK, "overworld", true, 2.8F, 6.0F);
	
	public static final Block WOODPLANKS = new BlockWoodPlanks("planks");
	public static final Block WOODLOGS = new BlockWoodLogs("log");
	public static final Block TREELEAVES = new BlockTreeLeaves("leaves");
	public static final Block TREESAPLINGS = new BlockTreeSaplings("sapling");
	
	public static final Block COUNTER = new BlockCounter("counter", Material.CLAY);
	public static final Block COUNTER_CORNER = new BlockCounter("counter_corner", Material.CLAY);
	public static final Block COUNTER_END = new BlockCounter("counter_end", Material.CLAY);
	
	public static final Block MICROSCOPE = new BlockMicroscope("microscope", Material.IRON);
	public static final Block ATOMIC_SCANNER = new BlockAtomicScanner("atomic_scanner", Material.GLASS);
	public static final Block TEST_TUBE_RACK = new BlockTestTubeRack("test_tube_rack", Material.IRON);
}
