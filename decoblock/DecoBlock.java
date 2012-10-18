package net.minecraft.decoblock;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.decoblock.base.*;
import net.minecraft.src.*;

public class DecoBlock extends BaseBlock
{
	public DecoBlock(int i, int renderType)
	{
		super(i, renderType);
		this.blockIndexInTexture = Block.cloth.getBlockTextureFromSideAndMetadata(0, 6);
	}

	@Override
	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess) {
		return new RenderDecoBlock(iblockaccess);
	}
}