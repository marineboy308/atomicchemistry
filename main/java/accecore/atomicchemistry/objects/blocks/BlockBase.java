package accecore.atomicchemistry.objects.blocks;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
	
	public static Boolean canCollide = false;
	
	public BlockBase(String name, Material material, SoundType soundtype, Boolean creativetab, Boolean canCollide, Boolean unbreakable, Float hardness, Float resistance) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(soundtype);
		if (creativetab) {
			setCreativeTab(Main.ATOMICCHEMISTRY);
		}
		if (unbreakable) {
			setBlockUnbreakable();
		}
		setHardness(hardness);
		setResistance(resistance);
		
		this.canCollide = canCollide;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return this.canCollide;
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}