package net.minecraft.decoblock.angleblock;

import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class RenderAngleBlock extends RenderBaseBlock
{
	public RenderAngleBlock(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);

		posY = -1.0D;
		maxIndex = 4;
		x = new double[maxIndex];
		y = new double[maxIndex];

		for(int i = 0; i < maxIndex; i++)
		{
			double o = (30D + (i * 120D)) * Math.PI / 180.0D;
			double cos = Math.cos(o);
			double sin = Math.sin(o);
			x[i] = cos;
			y[i] = sin;
		}
		x[3] = x[0];
		y[3] = y[0];
	}
}
