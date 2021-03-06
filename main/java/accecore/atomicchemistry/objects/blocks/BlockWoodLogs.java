package accecore.atomicchemistry.objects.blocks;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.objects.blocks.item.ItemBlockVariants;
import accecore.atomicchemistry.util.handlers.EnumHandler;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import accecore.atomicchemistry.util.interfaces.IMetaName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockWoodLogs extends BlockLog implements IMetaName, IHasModel
{
	public static final PropertyEnum<EnumHandler.EnumTypeTree> VARIANT = PropertyEnum.<EnumHandler.EnumTypeTree>create("variant", EnumHandler.EnumTypeTree.class, new Predicate<EnumHandler.EnumTypeTree>()
	{
		public boolean apply(@Nullable EnumHandler.EnumTypeTree apply)
		{
			return apply.getMeta() < 2;
		}
	});
	
	private String name;
	
	public BlockWoodLogs(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumTypeTree.RUBBER).withProperty(LOG_AXIS, EnumAxis.Y));
		setCreativeTab(Main.ATOMICCHEMISTRY);
		
		this.name = name;
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		for(EnumHandler.EnumTypeTree customblockplanks$enumtype : EnumHandler.EnumTypeTree.values())
		{
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumTypeTree.byMetadata((meta & 1) % 2));
		
		switch(meta & 6)
		{
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;
			
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;
			
		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;
			
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
		
		return state;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
		i = i | ((EnumHandler.EnumTypeTree)state.getValue(VARIANT)).getMeta();
		
		switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
		{
		case X:
			i |= 2;
			break;
			
		case Y:
			i |= 4;
			break;
			
		case Z:
			i |= 6;
		}
		
		return i;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT,LOG_AXIS});
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumHandler.EnumTypeTree)state.getValue(VARIANT)).getMeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
		return ((EnumHandler.EnumTypeTree)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public String getSpecialName(ItemStack stack) 
	{
		return EnumHandler.EnumTypeTree.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels() 
	{
		for(int i = 0; i < EnumHandler.EnumTypeTree.values().length; i++)
		{
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "log_" + EnumHandler.EnumTypeTree.values()[i].getName(), "inventory");
		}
	}	
}