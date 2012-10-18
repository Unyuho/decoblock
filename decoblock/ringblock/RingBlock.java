package net.minecraft.decoblock.ringblock;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class RingBlock extends BaseBlock
{
	public RingBlock(int i, int renderType)
	{
		super(i, renderType);
		this.blockIndexInTexture = Block.blockSteel.blockIndexInTexture;
	}

	@Override
	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess) {
		return new RenderRingBlock(iblockaccess);
	}
}