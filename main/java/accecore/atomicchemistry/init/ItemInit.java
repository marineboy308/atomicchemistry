package accecore.atomicchemistry.init;

import java.util.ArrayList;
import java.util.List;

import accecore.atomicchemistry.objects.items.ItemTestTube;
import net.minecraft.item.Item;

public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item TUBE_TEST = new ItemTestTube("tube_test");
}
