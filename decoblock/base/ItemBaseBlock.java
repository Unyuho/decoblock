package net.minecraft.decoblock.base;

import net.minecraft.src.*;

public class ItemBaseBlock extends ItemBlock
{
	public ItemBaseBlock(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int par1)
	{
    	return par1;
	}
}