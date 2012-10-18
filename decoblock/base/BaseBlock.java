package net.minecraft.decoblock.base;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class BaseBlock extends BlockContainer
{
	private final int renderType;

	public BaseBlock(int i, int renderType)
	{
		super(i, Block.planks.blockMaterial);
		this.blockIndexInTexture = Block.cloth.getBlockTextureFromSideAndMetadata(0, 6);
		this.renderType = renderType;

		setLightValue(1.0F);
		setHardness(Block.cloth.getHardness());
	}

	@Override
	public int getRenderType()
	{
		return renderType;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving);
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		int i = MathHelper.floor_double((double)((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int k = 0;
		MovingObjectPosition objectMouseOver = ModLoader.getMinecraftInstance().objectMouseOver;
		double hitX = (double)objectMouseOver.hitVec.xCoord - (double)objectMouseOver.blockX;
		double hitZ = (double)objectMouseOver.hitVec.zCoord - (double)objectMouseOver.blockZ;

		if(i == 0)
		{
			if(hitZ == 0.0D || hitZ >= 0.5D){
				k = 4;
			}else{
				k = 5;
			}
		}
		else if(i == 2)
		{
			if(hitZ == 1.0D || hitZ <= 0.5D){
				k = 3;
			}else{
				k = 5;
			}
		}
		else if(i == 1)
		{
			if(hitX == 1.0D || hitX <= 0.5D){
				k = 0;
			}else{
				k = 2;
			}
		}
		else if(i == 3)
		{
			if(hitX == 0.0D || hitX >= 0.5D){
				k = 1;
			}else{
				k = 2;
			}
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, k | meta);
	}

	@Override
	protected int damageDropped(int par1)
	{
		if((par1 & 8 ) == 0){
			return 0;
		}else{
			return 8;
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int i = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		int j = i & 7;

		if (j == 0){
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.2F, 1.0F, 1.0F);
		}else if (j == 1){
			setBlockBounds(0.8F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}else if (j == 2){
			setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
		}else if (j == 3){
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.2F);
		}else if (j == 4){
			setBlockBounds(0.0F, 0.0F, 0.8F, 1.0F, 1.0F, 1.0F);
		}else if (j == 5){
			setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 1.0F, 0.6F);
		}
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
	}

	@Override
	public TileEntity getBlockEntity()
	{
		return new TileEntityBaseBlock();
	}


	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess, int meta)
	{
		return getRenderBlocks(iblockaccess);
	}

	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess)
	{
		return new RenderBaseBlock(iblockaccess);
	}
}