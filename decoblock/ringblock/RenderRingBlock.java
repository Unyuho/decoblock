package net.minecraft.decoblock.ringblock;

import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class RenderRingBlock extends RenderBaseBlock
{
	public RenderRingBlock(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);

		moveY = -0.2D;
		posY = -1.0D;
		maxIndex = 73;
		x = new double[maxIndex];
		y = new double[maxIndex];

		for(int i = 0; i < maxIndex; i++)
		{
			double o = (i * 5D) * Math.PI / 180.0D;
			x[i] = Math.sin(o) * 0.6D;
			y[i] = Math.cos(o) * 0.6D;
		}
	}
}
