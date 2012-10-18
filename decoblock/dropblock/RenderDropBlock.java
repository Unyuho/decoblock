package net.minecraft.decoblock.dropblock;

import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class RenderDropBlock extends RenderBaseBlock
{
	public RenderDropBlock(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);

		moveY = -0.25D;
		posY = -1.0D;
		maxIndex = 73;
		x = new double[maxIndex];
		y = new double[maxIndex];

		for(int i = 0; i < maxIndex; i++)
		{
			double o = (90D + (i * 5D)) * Math.PI / 180.0D;
			double cos = Math.cos(o/4D);
			double sin = Math.sin(o/4D);

			double r = Math.pow(Math.pow(Math.abs(sin),2D / 3D) + Math.pow(Math.abs(cos),2D / 3D),-3D / 2D);

			x[i] = r * Math.sin(o) * 1D;
			y[i] = r * Math.cos(o) * 1D;
		}
	}
}
