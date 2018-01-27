package accecore.atomicchemistry.objects.items;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTestTube extends Item implements IHasModel {

	public ItemTestTube(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ATOMICCHEMISTRY);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
