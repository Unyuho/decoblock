package net.minecraft.decoblock.dropblock;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class DropBlock extends BaseBlock
{
	public DropBlock(int i, int renderType)
	{
		super(i, renderType);
		this.blockIndexInTexture = Block.ice.blockIndexInTexture;
	}

	@Override
	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess) {
		return new RenderDropBlock(iblockaccess);
	}
}