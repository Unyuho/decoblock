package net.minecraft.src;

import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.decoblock.*;
import net.minecraft.decoblock.angleblock.*;
import net.minecraft.decoblock.base.*;
import net.minecraft.decoblock.dropblock.*;
import net.minecraft.decoblock.propellerblock.*;
import net.minecraft.decoblock.ringblock.*;

public class mod_DecoBlock extends BaseMod
{
	@MLProp
	public static int DecoBlockID = 180;
	@MLProp
	public static int PropellerBlockID = 181;
	@MLProp
	public static int DropBlockID = 182;
	@MLProp
	public static int AngleBlockID = 183;
	@MLProp
	public static int RingBlockID = 184;
	@MLProp
	public static boolean render = false;

	public static Block decoBlock = null;
	public static Block propellerBlock = null;
	public static Block dropBlock = null;
	public static Block angleBlock = null;
	public static Block ringBlock = null;

	@Override
	public String getVersion()
	{
		return "1.0.0";
	}

	@Override
	public void load()
	{

		ModLoader.registerTileEntity(TileEntityBaseBlock.class, "decoblock", new BaseBlockRenderer());

		//ハートブロック
		int renderType =  ModLoader.getUniqueBlockModelID(this, true);

		decoBlock = new DecoBlock(DecoBlockID, renderType);
		decoBlock.setBlockName("DecoBlock");
		ModLoader.addName(decoBlock, "DecoBlock");
		ModLoader.registerBlock(decoBlock, ItemBaseBlock.class);

		ModLoader.addRecipe(new ItemStack(decoBlock, 1),
				new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Item.sugar), 'Y', new ItemStack(Block.plantRed) }
		);

		//プロペラブロック
		renderType =  ModLoader.getUniqueBlockModelID(this, true);

		propellerBlock = new PropellerBlock(PropellerBlockID, renderType);
		propellerBlock.setBlockName("PropellerBlock");
		ModLoader.addName(propellerBlock, "PropellerBlock");
		ModLoader.registerBlock(propellerBlock, ItemBaseBlock.class);

		ModLoader.addRecipe(new ItemStack(propellerBlock, 1),
				new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Item.stick), 'Y', new ItemStack(Block.planks,1,-1) }
		);

		//雫ブロック
		renderType =  ModLoader.getUniqueBlockModelID(this, true);

		dropBlock = new DropBlock(DropBlockID, renderType);
		dropBlock.setBlockName("DropBlock");
		ModLoader.addName(dropBlock, "DropBlock");
		ModLoader.registerBlock(dropBlock, ItemBaseBlock.class);

		ModLoader.addRecipe(new ItemStack(dropBlock, 1),
				new Object[] {"XXX", "XYX", "XXX", 'X', new ItemStack(Item.snowball), 'Y', new ItemStack(Item.bucketWater) }
		);

		//三角ブロック
		renderType =  ModLoader.getUniqueBlockModelID(this, true);

		angleBlock = new AngleBlock(AngleBlockID, renderType);
		angleBlock.setBlockName("AngleBlock");
		ModLoader.addName(angleBlock, "AngleBlock");
		ModLoader.registerBlock(angleBlock, ItemBaseBlock.class);

		ModLoader.addRecipe(new ItemStack(angleBlock, 1, 0),
				new Object[] {"X X", " X ", " X ", 'X', new ItemStack(Item.ingotIron) }
		);

		ModLoader.addRecipe(new ItemStack(angleBlock, 1, 8),
				new Object[] {" X ", " X ", "X X", 'X', new ItemStack(Item.ingotIron) }
		);

		//三角ブロック
		renderType =  ModLoader.getUniqueBlockModelID(this, true);

		ringBlock = new RingBlock(RingBlockID, renderType);
		ringBlock.setBlockName("RingBlock");
		ModLoader.addName(ringBlock, "RingBlock");
		ModLoader.registerBlock(ringBlock, ItemBaseBlock.class);

		ModLoader.addRecipe(new ItemStack(ringBlock, 1),
				new Object[] {"X X", "   ", "X X", 'X', new ItemStack(Item.ingotIron) }
		);

	}

	@Override
	public void renderInvBlock(RenderBlocks renderblocks, Block block, int i, int j)
	{
		RenderBaseBlock customrenderblocks = null;

		if(block instanceof BaseBlock){
			customrenderblocks = ((BaseBlock)block).getRenderBlocks(renderblocks.blockAccess, i);
		}else{
			customrenderblocks = new RenderBaseBlock(renderblocks.blockAccess);
		}
		customrenderblocks.useInventoryTint = false;
		customrenderblocks.renderBlockAsItemCustom(block, i, j);
		customrenderblocks.useInventoryTint = true;
	}

	@Override
	public boolean renderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
	{
		if(!render) return false;

		RenderBaseBlock customrenderblocks = null;

		if(block instanceof BaseBlock){
			int meta = iblockaccess.getBlockMetadata(i, j, k);
			customrenderblocks = ((BaseBlock)block).getRenderBlocks(renderblocks.blockAccess, meta);
		}else{
			customrenderblocks = new RenderBaseBlock(renderblocks.blockAccess);
		}

		customrenderblocks.overrideBlockTexture = renderblocks.overrideBlockTexture;
		customrenderblocks.renderBlockByRenderTypeCustom(block, i, j, k);
		return true;
	}
}
