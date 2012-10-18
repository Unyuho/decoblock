package net.minecraft.decoblock.angleblock;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class AngleBlock extends BaseBlock
{
	public AngleBlock(int i, int renderType)
	{
		super(i, renderType);
		this.blockIndexInTexture = Block.blockSteel.blockIndexInTexture;
	}

	@Override
	public RenderBaseBlock getRenderBlocks(IBlockAccess iblockaccess, int meta)
	{
		if((meta & 8 )== 0){
			return new RenderAngleBlock(iblockaccess);
		}else{
			return new RenderAngleRBlock(iblockaccess);
		}
	}
}