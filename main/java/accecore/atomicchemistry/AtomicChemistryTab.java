package accecore.atomicchemistry;

import accecore.atomicchemistry.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AtomicChemistryTab extends CreativeTabs {
	public AtomicChemistryTab(String label) { super("atomic_chemistrytab"); }
	public ItemStack getTabIconItem() { return new ItemStack(BlockInit.MICROSCOPE);}
}