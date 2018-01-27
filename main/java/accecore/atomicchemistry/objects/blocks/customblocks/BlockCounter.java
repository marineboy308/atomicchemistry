package accecore.atomicchemistry.objects.blocks.customblocks;

import java.util.List;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCounter extends Block implements IHasModel {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockCounter(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ATOMICCHEMISTRY);
		setHarvestLevel("pickaxe", 1);
		
		setResistance(7.0F);
		setHardness(2.0F);
		
		setSoundType(SoundType.STONE);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add("Lab equipment can be place on this.");
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	 protected BlockStateContainer createBlockState() {
		 return new BlockStateContainer(this, FACING);
	 }

	 @Override
	 public int getMetaFromState(IBlockState state) {
	 	return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	 }

	 @Override
	 public IBlockState getStateFromMeta(int meta) {
	 	EnumFacing enumfacing = EnumFacing.getHorizontal(meta);

	 	if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
	    	enumfacing = EnumFacing.NORTH;
	    }

	    return this.getDefaultState().withProperty(FACING, enumfacing);
	 }

	 @Override
	 public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		 worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	 }

	 @Override
	 public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		 return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	 }
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
