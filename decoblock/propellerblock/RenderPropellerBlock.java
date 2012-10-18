package net.minecraft.decoblock.propellerblock;

import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.decoblock.base.*;

public class RenderPropellerBlock extends RenderBaseBlock
{
	public RenderPropellerBlock(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);

		posY = -1.0D;
		maxIndex = 73;
		x = new double[maxIndex];
		y = new double[maxIndex];

		for(int i = 0; i < maxIndex; i++)
		{
			double o = (90D + (i * 5D)) * Math.PI / 180.0D;
			double cos = Math.cos(3D*o/4D);
			double sin = Math.sin(3D*o/4D);

			//プロペラ
			double r = Math.pow(Math.abs(sin),20) + Math.pow(Math.abs(cos),20);

			x[i] = r * Math.cos(o) * 1D;
			y[i] = r * Math.sin(o) * 1D;
		}
	}
}
