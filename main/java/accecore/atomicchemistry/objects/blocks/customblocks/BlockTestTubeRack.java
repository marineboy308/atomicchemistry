package accecore.atomicchemistry.objects.blocks.customblocks;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTestTubeRack extends Block implements IHasModel {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB AABB_X = new AxisAlignedBB(0.125D, 0, 0.3125D, 0.875D, 0.4375D, 0.6875D);
	public static final AxisAlignedBB AABB_Z = new AxisAlignedBB(0.3125D, 0, 0.125D, 0.6875D, 0.4375D, 0.875D);

	public BlockTestTubeRack(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(Main.ATOMICCHEMISTRY);
		setHarvestLevel("pickaxe", 2);
		
		setResistance(1.0F);
		setHardness(1.2F);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		Block blockDown = world.getBlockState(pos.down()).getBlock();
		if(blockDown == BlockInit.COUNTER || blockDown == BlockInit.COUNTER_CORNER || blockDown == BlockInit.COUNTER_END){
			return true;
		}
		return false;

	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        return enumfacing.getAxis() == EnumFacing.Axis.Z ? AABB_X : AABB_Z;
    }
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
