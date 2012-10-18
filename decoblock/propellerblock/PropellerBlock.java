package net.minecraft.decoblock.propellerblock;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class PropellerBlock extends BaseBlock
{
	public PropellerBlock(int i, int renderType)
	{
		super(i, renderType);
		this.blockIndexInTexture = Block.planks.blockIndexInTexture;
	}

	@Override
	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess) {
		return new RenderPropellerBlock(iblockaccess);
	}
}