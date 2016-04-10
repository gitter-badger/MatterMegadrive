/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.client.render;

import matteroverdrive.client.RenderHandler;
import matteroverdrive.client.render.conversation.EntityRendererConversation;
import matteroverdrive.gui.GuiDialog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/**
 * Created by Simeon on 8/10/2015.
 */
public class RenderDialogSystem implements IWorldLastRenderer
{
	private final EntityRendererConversation entityRendererConversation;
	EntityRenderer lastEntityRenderer;

	public RenderDialogSystem()
	{
		entityRendererConversation = new EntityRendererConversation(Minecraft.getMinecraft(), Minecraft.getMinecraft().getResourceManager());
	}

	@Override
	public void onRenderWorldLast(RenderHandler handler, RenderWorldLastEvent event)
	{
		if (Minecraft.getMinecraft().currentScreen instanceof GuiDialog)
		{
			if (lastEntityRenderer == null)
			{
				lastEntityRenderer = Minecraft.getMinecraft().entityRenderer;
			}

			Minecraft.getMinecraft().entityRenderer = entityRendererConversation;
		}
		else
		{
			if (lastEntityRenderer != null)
			{
				Minecraft.getMinecraft().entityRenderer = lastEntityRenderer;
				lastEntityRenderer = null;
			}
		}
	}
}
