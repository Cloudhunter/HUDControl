package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ArmourRenderInfo extends RenderInfo<RenderGameOverlayEvent.ElementType>
{
    public ArmourRenderInfo(OffsetType type)
    {
        super(RenderGameOverlayEvent.ElementType.ARMOR, type);
    }

    @Override
    public int getWidth(ScaledResolution sr)
    {
        return 81;
    }

    @Override
    public int getHeight(ScaledResolution sr)
    {
        return 9;
    }

    @Override
    public int getBaseX(ScaledResolution sr)
    {
        return sr.getScaledWidth() / 2 - 91;
    }

    @Override
    public int getBaseY(ScaledResolution sr)
    {
        EntityPlayer entityplayer = Minecraft.getMinecraft().player;
        IAttributeInstance iattributeinstance = entityplayer.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        int k1 = MathHelper.ceil(entityplayer.getAbsorptionAmount());
        float f = (float)iattributeinstance.getAttributeValue();
        int l1 = MathHelper.ceil((f + (float)k1) / 2.0F / 10.0F);
        int j1 = sr.getScaledHeight() - 39;
        int i2 = Math.max(10 - (l1 - 2), 3);
        return j1 - (l1 - 1) * i2 - 10;
    }
}
