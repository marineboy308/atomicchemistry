package accecore.atomicchemistry.objects.blocks;

import java.util.List;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import accecore.atomicchemistry.Main;
import accecore.atomicchemistry.init.BlockInit;
import accecore.atomicchemistry.init.ItemInit;
import accecore.atomicchemistry.objects.blocks.item.ItemBlockVariants;
import accecore.atomicchemistry.util.handlers.EnumHandler;
import accecore.atomicchemistry.util.interfaces.IHasModel;
import accecore.atomicchemistry.util.interfaces.IMetaName;
import accecore.atomicchemistry.world.gen.generators.WorldGenRubberTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;

public class BlockTreeLeaves extends BlockLeaves implements IMetaName, IHasModel
{
	public static final PropertyEnum<EnumHandler.EnumTypeTree> VARIANT = PropertyEnum.<EnumHandler.EnumTypeTree>create("variant", EnumHandler.EnumTypeTree.class, new Predicate<EnumHandler.EnumTypeTree>()
	{
		public boolean apply(@Nullable EnumHandler.EnumTypeTree apply)
		{
			return apply.getMeta() < 2;
		}
	});
	
	private String name;
	
	public BlockTreeLeaves(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumTypeTree.RUBBER).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
		setCreativeTab(Main.ATOMICCHEMISTRY);
		
		this.name = name;
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumTypeTree.RUBBER).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(false)));
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockInit.TREESAPLINGS);
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumTypeTree.byMetadata(meta % 2));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = ((EnumHandler.EnumTypeTree)state.getValue(VARIANT)).getMeta();
		
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue())
		{
			i |= 2;
		}
		
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
		{
			i |= 4;
		}
		
		return i;
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
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		return;
	}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {
		return 30;
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) 
	{
		return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT,DECAYABLE,CHECK_DECAY });
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.TRANSLUCENT;
	}	
	
	@Override
	public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int chance = this.getSaplingDropChance(state);

        if (fortune > 0)
        {
            chance -= 2 << fortune;
            if (chance < 10) chance = 10;
        }

        if (rand.nextInt(chance) == 0)
        {
            ItemStack drop = new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state));
            if (!drop.isEmpty())
                drops.add(drop);
        }

        chance = 200;
        if (fortune > 0)
        {
            chance -= 10 << fortune;
            if (chance < 40) chance = 40;
        }

        this.captureDrops(true);
        if (world instanceof World)
            this.dropApple((World)world, pos, state, chance);
        drops.addAll(this.captureDrops(false));
    }
	
	@Override
	public void registerModels() 
	{
		for(int i = 0; i < EnumHandler.EnumTypeTree.values().length; i++)
		{
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "leaves_" + EnumHandler.EnumTypeTree.values()[i].getName(), "inventory");
		}
	}
}