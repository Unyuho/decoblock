package net.minecraft.decoblock.base;

import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class RenderBaseBlock extends RenderBlocks
{
	public int maxIndex;
	public double x[];
	public double y[];
	public double posY;
	public double moveY;

	public RenderBaseBlock(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);

		maxIndex = 37;
		x = new double[maxIndex];
		y = new double[maxIndex];
		posY = 1.0D;
		moveY = 0.0D;
		/*
		for(int i = 0; i <= 36; i++)
		{
			double o = (i * 5D) * Math.PI / 180.0D;
			double cos = Math.cos(o);
			double sin = Math.sin(o);
			double r = Math.abs(sin) * (1 + Math.abs(sin*cos));

			x[i] = r * cos * 1.2D;
			y[i] = r * sin * 1.4D;
		}
		*/
	}

	public boolean renderBlockByRenderTypeCustom(Block par1Block, int par2, int par3, int par4)
	{
		par1Block.setBlockBoundsBasedOnState(blockAccess, par2, par3, par4);
		return renderBaseBlock(par1Block, par2, par3, par4);
	}

	public void renderBlockAsItemCustom(Block renderBlock, int renderType , float size)
	{
		Tessellator tessellator = Tessellator.instance;

		renderBlock.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderFavNorthFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderFavSouthFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1F, 0.0F);
		renderNSRingFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0));
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

	}

	public boolean renderBaseBlock(Block par1Block, int par2, int par3, int par4)
	{
		int i = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
		float f = (float)(i >> 16 & 0xff) / 255F;
		float f1 = (float)(i >> 8 & 0xff) / 255F;
		float f2 = (float)(i & 0xff) / 255F;

		if (EntityRenderer.anaglyphEnable)
		{
			float f3 = (f * 30F + f1 * 59F + f2 * 11F) / 100F;
			float f4 = (f * 30F + f1 * 70F) / 100F;
			float f5 = (f * 30F + f2 * 70F) / 100F;
			f = f3;
			f1 = f4;
			f2 = f5;
		}

		return renderBaseBlockWithColorMultiplier(par1Block, par2, par3, par4, f, f1, f2);
	}


	public boolean renderBaseBlockWithColorMultiplier(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7)
	{
		enableAO = false;
		Tessellator tessellator = Tessellator.instance;
		boolean flag = false;
		float f = 0.5F;
		float f1 = 1.0F;
		float f2 = 0.8F;
		float f3 = 0.6F;
		float f4 = f1 * par5;
		float f5 = f1 * par6;
		float f6 = f1 * par7;
		float f7 = f;
		float f8 = f2;
		float f9 = f3;
		float f10 = f;
		float f11 = f2;
		float f12 = f3;
		float f13 = f;
		float f14 = f2;
		float f15 = f3;

		if (par1Block != Block.grass)
		{
			f7 = f * par5;
			f8 = f2 * par5;
			f9 = f3 * par5;
			f10 = f * par6;
			f11 = f2 * par6;
			f12 = f3 * par6;
			f13 = f * par7;
			f14 = f2 * par7;
			f15 = f3 * par7;
		}


		par1Block.setBlockBoundsBasedOnState(blockAccess, par2, par3, par4);

		int i = par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4);
		int meta = blockAccess.getBlockMetadata(par2, par3, par4);
		int side = meta & 7;

		int j = par1Block.getBlockTexture(blockAccess, par2, par3, par4, 2);
		if(side > 2){

			if(par1Block.shouldSideBeRendered(blockAccess, par2, par3, par4 - 1, 2)){
				tessellator.setBrightness(par1Block.minZ <= 0.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4 - 1) : i);
				tessellator.setColorOpaque_F(f8, f11, f14);
				renderDecoEastFace(par1Block, par2, par3, par4, j);
			}

			if(par1Block.shouldSideBeRendered(blockAccess, par2, par3, par4 + 1, 3)){
				tessellator.setBrightness(par1Block.maxZ >= 1.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4 + 1) : i);
				tessellator.setColorOpaque_F(f8, f11, f14);
				renderFavWestFace(par1Block, par2, par3, par4, j);
			}

			tessellator.setBrightness(i);
			tessellator.setColorOpaque_F(f4, f5, f6);
			renderEWRingFace(par1Block, par2, par3, par4, j);

		}else{
			if(par1Block.shouldSideBeRendered(blockAccess, par2 - 1, par3, par4, 4)){
				tessellator.setBrightness(par1Block.minX <= 0.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2 - 1, par3, par4) : i);
				tessellator.setColorOpaque_F(f9, f12, f15);
				renderFavNorthFace(par1Block, par2, par3, par4, j);
			}

			if(par1Block.shouldSideBeRendered(blockAccess, par2 + 1, par3, par4, 5)){
				tessellator.setBrightness(par1Block.maxX >= 1.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2 + 1, par3, par4) : i);
				tessellator.setColorOpaque_F(f9, f12, f15);
				renderFavSouthFace(par1Block, par2, par3, par4, j);
			}

			tessellator.setBrightness(i);
			tessellator.setColorOpaque_F(f4, f5, f6);
			renderNSRingFace(par1Block, par2, par3, par4, j);
		}
		return true;
	}


	public void renderNSRingFace(Block par1Block, double par2, double par4, double par6, int par8)
	{

		Tessellator tessellator = Tessellator.instance;

		if (this.overrideBlockTexture >= 0)
		{
			par8 = this.overrideBlockTexture;
		}

		int var10 = (par8 & 0xf) << 4;
		int var11 = par8 & 0xf0;

		double minU = ((double)var10 + par1Block.minZ * 16.0D) / 256D;
		double maxU = ((double)var10 + par1Block.maxZ * 16.0D - 0.01D) / 256D;
		double maxV = ((double)(var11 + 16) - par1Block.maxY * 16.0D) / 256D;
		double minV = ((double)(var11 + 16) - par1Block.minY * 16.0D - 0.01D) / 256D;

		if (par1Block.minZ < 0.0D || par1Block.maxZ > 1.0D)
		{
			minU = (double)(((float)var10 + 0.0F) / (float)256D);
			maxU = (double)(((float)var10 + 15.99F) / (float)256D);
		}

		if (par1Block.minY < 0.0D || par1Block.maxY > 1.0D)
		{
			maxV = (double)(((float)var11 + 0.0F) / (float)256D);
			minV = (double)(((float)var11 + 15.99F) / (float)256D);
		}

		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double xR = (minZ - maxZ) / 2D;
		double yR = (minY - maxY) / 2D;
		double uR = (minU - maxU) / 2D;
		double vR = (minV - maxV) / 2D;

		double x0 = maxZ + xR;
		double y0 = (minY + yR * posY ) + moveY;
		double u0 = maxU + uR;
		double v0 = minV + vR * posY;

		double x1;
		double y1;
		double u1;
		double v1;
		double x2;
		double y2;
		double u2;
		double v2;

		for(int i = 0; i < maxIndex-1; i++)
		{
			x1 = x0 - xR * x[i];
			y1 = y0 - yR * y[i];
			u1 = u0 - uR * x[i];
			v1 = v0 - vR * y[i];

			x2 = x0 - xR * x[i+1];
			y2 = y0 - yR * y[i+1];
			u2 = u0 - uR * x[i+1];
			v2 = v0 - vR * y[i+1];

			tessellator.addVertexWithUV(minX, y1, x1, minU, minV);
			tessellator.addVertexWithUV(maxX, y1, x1, minU, maxV);
			tessellator.addVertexWithUV(maxX, y2, x2, maxU, maxV);
			tessellator.addVertexWithUV(minX, y2, x2, maxU, minV);
		}
	}


	public void renderFavNorthFace(Block par1Block, double par2, double par4, double par6, int par8)
	{
		Tessellator tessellator = Tessellator.instance;

		if (this.overrideBlockTexture >= 0)
		{
			par8 = this.overrideBlockTexture;
		}

		int var10 = (par8 & 0xf) << 4;
		int var11 = par8 & 0xf0;

		double minU = ((double)var10 + par1Block.minZ * 16.0D) / 256D;
		double maxU = ((double)var10 + par1Block.maxZ * 16.0D - 0.01D) / 256D;
		double maxV = ((double)(var11 + 16) - par1Block.maxY * 16.0D) / 256D;
		double minV = ((double)(var11 + 16) - par1Block.minY * 16.0D - 0.01D) / 256D;

		if (par1Block.minZ < 0.0D || par1Block.maxZ > 1.0D)
		{
			minU = (double)(((float)var10 + 0.0F) / (float)256D);
			maxU = (double)(((float)var10 + 15.99F) / (float)256D);
		}

		if (par1Block.minY < 0.0D || par1Block.maxY > 1.0D)
		{
			maxV = (double)(((float)var11 + 0.0F) / (float)256D);
			minV = (double)(((float)var11 + 15.99F) / (float)256D);
		}

		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double xR = (minZ - maxZ) / 2D;
		double yR = (minY - maxY) / 2D;
		double uR = (minU - maxU) / 2D;
		double vR = (minV - maxV) / 2D;

		double x0 = maxZ + xR;
		double y0 = (minY + yR * posY ) + moveY;
		double u0 = maxU + uR;
		double v0 = minV + vR * posY;

		double x1;
		double y1;
		double u1;
		double v1;
		double x2;
		double y2;
		double u2;
		double v2;
		double x3;
		double y3;
		double u3;
		double v3;

		for(int i = 0; i < maxIndex; i = i + 2)
		{
			x1 = x0 - xR * x[i];
			y1 = y0 - yR * y[i];
			u1 = u0 - uR * x[i];
			v1 = v0 - vR * y[i];

			if(i+1 < maxIndex){
				x2 = x0 - xR * x[i+1];
				y2 = y0 - yR * y[i+1];
				u2 = u0 - uR * x[i+1];
				v2 = v0 - vR * y[i+1];
			}else{
				x2 = x0;
				y2 = y0;
				u2 = u0;
				v2 = v0;
			}

			if(i+2 < maxIndex){
				x3 = x0 - xR * x[i+2];
				y3 = y0 - yR * y[i+2];
				u3 = u0 - uR * x[i+2];
				v3 = v0 - vR * y[i+2];
			}else{
				x3 = x0;
				y3 = y0;
				u3 = u0;
				v3 = v0;
			}

			tessellator.addVertexWithUV(minX, y0, x0, u0, v0);
			tessellator.addVertexWithUV(minX, y1, x1, u1, v1);
			tessellator.addVertexWithUV(minX, y2, x2, u2, v2);
			tessellator.addVertexWithUV(minX, y3, x3, u3, v3);
		}
	}

	public void renderFavSouthFace(Block par1Block, double par2, double par4, double par6, int par8)
	{
		Tessellator tessellator = Tessellator.instance;

		if (this.overrideBlockTexture >= 0)
		{
			par8 = this.overrideBlockTexture;
		}

		int var10 = (par8 & 0xf) << 4;
		int var11 = par8 & 0xf0;

		double minU = ((double)var10 + par1Block.minZ * 16.0D) / 256D;
		double maxU = ((double)var10 + par1Block.maxZ * 16.0D - 0.01D) / 256D;
		double maxV = ((double)(var11 + 16) - par1Block.maxY * 16.0D) / 256D;
		double minV = ((double)(var11 + 16) - par1Block.minY * 16.0D - 0.01D) / 256D;

		if (par1Block.minZ < 0.0D || par1Block.maxZ > 1.0D)
		{
			minU = (double)(((float)var10 + 0.0F) / (float)256D);
			maxU = (double)(((float)var10 + 15.99F) / (float)256D);
		}

		if (par1Block.minY < 0.0D || par1Block.maxY > 1.0D)
		{
			maxV = (double)(((float)var11 + 0.0F) / (float)256D);
			minV = (double)(((float)var11 + 15.99F) / (float)256D);
		}

		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double xR = (minZ - maxZ) / 2D;
		double yR = (minY - maxY) / 2D;
		double uR = (minU - maxU) / 2D;
		double vR = (minV - maxV) / 2D;

		double x0 = maxZ + xR;
		double y0 = (minY + yR * posY ) + moveY;
		double u0 = maxU + uR;
		double v0 = minV + vR * posY;

		double x1;
		double y1;
		double u1;
		double v1;
		double x2;
		double y2;
		double u2;
		double v2;
		double x3;
		double y3;
		double u3;
		double v3;

		for(int i = 0; i < maxIndex; i = i + 2)
		{
			x1 = x0 - xR * x[i];
			y1 = y0 - yR * y[i];
			u1 = u0 - uR * x[i];
			v1 = v0 - vR * y[i];

			if(i+1 < maxIndex){
				x2 = x0 - xR * x[i+1];
				y2 = y0 - yR * y[i+1];
				u2 = u0 - uR * x[i+1];
				v2 = v0 - vR * y[i+1];
			}else{
				x2 = x0;
				y2 = y0;
				u2 = u0;
				v2 = v0;
			}

			if(i+2 < maxIndex){
				x3 = x0 - xR * x[i+2];
				y3 = y0 - yR * y[i+2];
				u3 = u0 - uR * x[i+2];
				v3 = v0 - vR * y[i+2];
			}else{
				x3 = x0;
				y3 = y0;
				u3 = u0;
				v3 = v0;
			}

			tessellator.addVertexWithUV(maxX, y0, x0, u0, v0);
			tessellator.addVertexWithUV(maxX, y3, x3, u3, v3);
			tessellator.addVertexWithUV(maxX, y2, x2, u2, v2);
			tessellator.addVertexWithUV(maxX, y1, x1, u1, v1);
		}
	}


	public void renderEWRingFace(Block par1Block, double par2, double par4, double par6, int par8)
	{

		Tessellator tessellator = Tessellator.instance;

		if (this.overrideBlockTexture >= 0)
		{
			par8 = this.overrideBlockTexture;
		}

		int var10 = (par8 & 0xf) << 4;
		int var11 = par8 & 0xf0;

		double minU = ((double)var10 + par1Block.minX * 16.0D) / 256D;
		double maxU = ((double)var10 + par1Block.maxX * 16.0D - 0.01D) / 256D;
		double maxV = ((double)(var11 + 16) - par1Block.maxY * 16.0D) / 256D;
		double minV = ((double)(var11 + 16) - par1Block.minY * 16.0D - 0.01D) / 256D;

		if (par1Block.minX < 0.0D || par1Block.maxX > 1.0D)
		{
			minU = (double)(((float)var10 + 0.0F) / (float)256D);
			maxU = (double)(((float)var10 + 15.99F) / (float)256D);
		}

		if (par1Block.minY < 0.0D || par1Block.maxY > 1.0D)
		{
			maxV = (double)(((float)var11 + 0.0F) / (float)256D);
			minV = (double)(((float)var11 + 15.99F) / (float)256D);
		}

		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double xR = (minX - maxX) / 2D;
		double yR = (minY - maxY) / 2D;
		double uR = (minU - maxU) / 2D;
		double vR = (minV - maxV) / 2D;

		double x0 = maxX + xR;
		double y0 = (minY + yR * posY ) + moveY;
		double u0 = maxU + uR;
		double v0 = minV + vR * posY;

		double x1;
		double y1;
		double u1;
		double v1;
		double x2;
		double y2;
		double u2;
		double v2;

		for(int i = 0; i < maxIndex-1; i++)
		{
			x1 = x0 - xR * x[i];
			y1 = y0 - yR * y[i];
			u1 = u0 - uR * x[i];
			v1 = v0 - vR * y[i];

			x2 = x0 - xR * x[i+1];
			y2 = y0 - yR * y[i+1];
			u2 = u0 - uR * x[i+1];
			v2 = v0 - vR * y[i+1];

			tessellator.addVertexWithUV(x1, y1, minZ, minU, minV);
			tessellator.addVertexWithUV(x2, y2, minZ, maxU, minV);
			tessellator.addVertexWithUV(x2, y2, maxZ, maxU, maxV);
			tessellator.addVertexWithUV(x1, y1, maxZ, minU, maxV);
		}
	}

	public void renderDecoEastFace(Block par1Block, double par2, double par4, double par6, int par8)
	{
		Tessellator tessellator = Tessellator.instance;

		if (this.overrideBlockTexture >= 0)
		{
			par8 = this.overrideBlockTexture;
		}

		int var10 = (par8 & 0xf) << 4;
		int var11 = par8 & 0xf0;

		double minU = ((double)var10 + par1Block.minX * 16.0D) / 256D;
		double maxU = ((double)var10 + par1Block.maxX * 16.0D - 0.01D) / 256D;
		double maxV = ((double)(var11 + 16) - par1Block.maxY * 16.0D) / 256D;
		double minV = ((double)(var11 + 16) - par1Block.minY * 16.0D - 0.01D) / 256D;

		if (par1Block.minX < 0.0D || par1Block.maxX > 1.0D)
		{
			minU = (double)(((float)var10 + 0.0F) / (float)256D);
			maxU = (double)(((float)var10 + 15.99F) / (float)256D);
		}

		if (par1Block.minY < 0.0D || par1Block.maxY > 1.0D)
		{
			maxV = (double)(((float)var11 + 0.0F) / (float)256D);
			minV = (double)(((float)var11 + 15.99F) / (float)256D);
		}

		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double xR = (minX - maxX) / 2D;
		double yR = (minY - maxY) / 2D;
		double uR = (minU - maxU) / 2D;
		double vR = (minV - maxV) / 2D;

		double x0 = maxX + xR;
		double y0 = (minY + yR * posY ) + moveY;
		double u0 = maxU + uR;
		double v0 = minV + vR * posY;

		double x1;
		double y1;
		double u1;
		double v1;
		double x2;
		double y2;
		double u2;
		double v2;
		double x3;
		double y3;
		double u3;
		double v3;

		for(int i = 0; i < maxIndex; i = i + 2)
		{
			x1 = x0 - xR * x[i];
			y1 = y0 - yR * y[i];
			u1 = u0 - uR * x[i];
			v1 = v0 - vR * y[i];

			if(i+1 < maxIndex){
				x2 = x0 - xR * x[i+1];
				y2 = y0 - yR * y[i+1];
				u2 = u0 - uR * x[i+1];
				v2 = v0 - vR * y[i+1];
			}else{
				x2 = x0;
				y2 = y0;
				u2 = u0;
				v2 = v0;
			}

			if(i+2 < maxIndex){
				x3 = x0 - xR * x[i+2];
				y3 = y0 - yR * y[i+2];
				u3 = u0 - uR * x[i+2];
				v3 = v0 - vR * y[i+2];
			}else{
				x3 = x0;
				y3 = y0;
				u3 = u0;
				v3 = v0;
			}

			tessellator.addVertexWithUV(x0, y0, minZ, u0, v0);
			tessellator.addVertexWithUV(x3, y3, minZ, u3, v3);
			tessellator.addVertexWithUV(x2, y2, minZ, u2, v2);
			tessellator.addVertexWithUV(x1, y1, minZ, u1, v1);
		}
	}


	public void renderFavWestFace(Block par1Block, double par2, double par4, double par6, int par8)
	{

		Tessellator tessellator = Tessellator.instance;

		if (this.overrideBlockTexture >= 0)
		{
			par8 = this.overrideBlockTexture;
		}

		int var10 = (par8 & 0xf) << 4;
		int var11 = par8 & 0xf0;

		double minU = ((double)var10 + par1Block.minX * 16.0D) / 256D;
		double maxU = ((double)var10 + par1Block.maxX * 16.0D - 0.01D) / 256D;
		double maxV = ((double)(var11 + 16) - par1Block.maxY * 16.0D) / 256D;
		double minV = ((double)(var11 + 16) - par1Block.minY * 16.0D - 0.01D) / 256D;

		if (par1Block.minX < 0.0D || par1Block.maxX > 1.0D)
		{
			minU = (double)(((float)var10 + 0.0F) / (float)256D);
			maxU = (double)(((float)var10 + 15.99F) / (float)256D);
		}

		if (par1Block.minY < 0.0D || par1Block.maxY > 1.0D)
		{
			maxV = (double)(((float)var11 + 0.0F) / (float)256D);
			minV = (double)(((float)var11 + 15.99F) / (float)256D);
		}

		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double xR = (minX - maxX) / 2D;
		double yR = (minY - maxY) / 2D;
		double uR = (minU - maxU) / 2D;
		double vR = (minV - maxV) / 2D;

		double x0 = maxX + xR;
		double y0 = (minY + yR * posY ) + moveY;
		double u0 = maxU + uR;
		double v0 = minV + vR * posY;

		double x1;
		double y1;
		double u1;
		double v1;
		double x2;
		double y2;
		double u2;
		double v2;
		double x3;
		double y3;
		double u3;
		double v3;

		for(int i = 0; i < maxIndex; i = i + 2)
		{
			x1 = x0 - xR * x[i];
			y1 = y0 - yR * y[i];
			u1 = u0 - uR * x[i];
			v1 = v0 - vR * y[i];

			if(i+1 < maxIndex){
				x2 = x0 - xR * x[i+1];
				y2 = y0 - yR * y[i+1];
				u2 = u0 - uR * x[i+1];
				v2 = v0 - vR * y[i+1];
			}else{
				x2 = x0;
				y2 = y0;
				u2 = u0;
				v2 = v0;
			}

			if(i+2 < maxIndex){
				x3 = x0 - xR * x[i+2];
				y3 = y0 - yR * y[i+2];
				u3 = u0 - uR * x[i+2];
				v3 = v0 - vR * y[i+2];
			}else{
				x3 = x0;
				y3 = y0;
				u3 = u0;
				v3 = v0;
			}

			tessellator.addVertexWithUV(x0, y0, maxZ, u0, v0);
			tessellator.addVertexWithUV(x1, y1, maxZ, u1, v1);
			tessellator.addVertexWithUV(x2, y2, maxZ, u2, v2);
			tessellator.addVertexWithUV(x3, y3, maxZ, u3, v3);
		}
	}
}
