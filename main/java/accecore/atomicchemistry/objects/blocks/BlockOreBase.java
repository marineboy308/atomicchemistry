package accecore.atomicchemistry.objects.blocks;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.objects.blocks.item.ItemBlockVariants;
import accecore.atomicchemistry.util.handlers.EnumHandler;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import accecore.atomicchemistry.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockOreBase extends Block implements IHasModel, IMetaName {
	
	public static final PropertyEnum<EnumHandler.EnumTypeOre> VARIANT = PropertyEnum.<EnumHandler.EnumTypeOre>create("variant", EnumHandler.EnumTypeOre.class);
	
	private String name, dimension;
	
	public BlockOreBase(String name, Material material, String dimension, Boolean creativetab, float hardness, float resistance) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		if (creativetab) {
			setCreativeTab(Main.ATOMICCHEMISTRY);
		}
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumTypeOre.COPPER));
		
		setHardness(hardness);
		setResistance(resistance);
		
		this.name = name;
		this.dimension = dimension;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.EnumTypeOre)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumHandler.EnumTypeOre)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumTypeOre.byMetadata(meta));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumTypeOre variant: EnumHandler.EnumTypeOre.values()) {
			items.add(new ItemStack(this, 1, variant.getMeta()));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumTypeOre.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumHandler.EnumTypeOre.values().length; i++) {
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "ore_" + this.dimension + "_" + EnumHandler.EnumTypeOre.values()[i].getName(), "inventory");
		}
	}
}