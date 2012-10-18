package net.minecraft.decoblock.base;

import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class BaseBlockRenderer extends TileEntitySpecialRenderer
{
	public BaseBlockRenderer()
	{
	}

	@Override
	public void renderTileEntityAt(TileEntity par1tileentity, double d, double d1, double d2, float f)
	{
		if (mod_DecoBlock.render)
		{
			return;
		}

		float f1 = 1.0F;
		GL11.glPushMatrix();
		GL11.glTranslated(d, d1, d2);
		GL11.glTranslated(-par1tileentity.xCoord, -par1tileentity.yCoord, -par1tileentity.zCoord);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScaled(f1, f1, f1);
		RenderHelper.disableStandardItemLighting();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);

		if (Minecraft.isAmbientOcclusionEnabled())
		{
			GL11.glShadeModel(GL11.GL_SMOOTH);
		}
		else
		{
			GL11.glShadeModel(GL11.GL_FLAT);
		}

		Tessellator.instance.startDrawingQuads();
		bindTextureByName("/terrain.png");

		RenderBaseBlock renderdeco = null;

		Block block = Block.blocksList[par1tileentity.worldObj.getBlockId(par1tileentity.xCoord, par1tileentity.yCoord, par1tileentity.zCoord)];
		int meta = par1tileentity.worldObj.getBlockMetadata(par1tileentity.xCoord, par1tileentity.yCoord, par1tileentity.zCoord);
		if(block instanceof BaseBlock){
			renderdeco = ((BaseBlock)block).getRenderBlocks(par1tileentity.worldObj, meta);
		}else{
			renderdeco = new RenderBaseBlock(par1tileentity.worldObj);
		}

		renderdeco.renderBlockByRenderTypeCustom(block, par1tileentity.xCoord, par1tileentity.yCoord, par1tileentity.zCoord);

		Tessellator.instance.draw();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
}
