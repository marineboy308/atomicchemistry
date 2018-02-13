package accecore.atomicchemistry.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

	public static enum EnumTypeOre implements IStringSerializable {
		
		COPPER(0,"copper"),
		ALUMINIUM(1,"aluminium");
		
		private static final EnumTypeOre[] META_LOOKUP = new EnumTypeOre[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumTypeOre(int meta, String name) {
			this(meta,name,name);
		}
		
		private EnumTypeOre(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name= name;
			this.unlocalizedName = unlocalizedName;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public static EnumTypeOre byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for (EnumTypeOre enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
	
	public static enum EnumTypeTree implements IStringSerializable {
		
		RUBBER(0,"rubber"),
		ATOM(1,"atom");
		
		private static final EnumTypeTree[] META_LOOKUP = new EnumTypeTree[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumTypeTree(int meta, String name) {
			this(meta,name,name);
		}
		
		private EnumTypeTree(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name= name;
			this.unlocalizedName = unlocalizedName;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public static EnumTypeTree byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for (EnumTypeTree enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
	
	public static enum EnumTypeMaterials implements IStringSerializable {
		
		ATOMIC(0,"atomic");
		
		private static final EnumTypeMaterials[] META_LOOKUP = new EnumTypeMaterials[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumTypeMaterials(int meta, String name) {
			this(meta,name,name);
		}
		
		private EnumTypeMaterials(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name= name;
			this.unlocalizedName = unlocalizedName;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public static EnumTypeMaterials byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for (EnumTypeMaterials enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}
