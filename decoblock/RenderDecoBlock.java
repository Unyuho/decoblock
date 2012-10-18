package net.minecraft.decoblock;

import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.decoblock.base.*;
import net.minecraft.src.*;

public class RenderDecoBlock extends RenderBaseBlock
{
	public RenderDecoBlock(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);

		posY = 0.0D;
		maxIndex = 37;
		x = new double[maxIndex];
		y = new double[maxIndex];

		for(int i = 0; i < maxIndex; i++)
		{
			double o = (i * 5D) * Math.PI / 180.0D;
			double cos = Math.cos(o);
			double sin = Math.sin(o);
			double r = Math.abs(sin) * (1 + Math.abs(sin*cos));

			x[i] = r * cos * 1.2D;
			y[i] = r * sin * 1.4D;
		}
	}
}
