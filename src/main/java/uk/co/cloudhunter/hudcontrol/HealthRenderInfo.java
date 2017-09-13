package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HealthRenderInfo extends RenderInfo<RenderGameOverlayEvent.ElementType>
{
    public HealthRenderInfo(OffsetType offsetType)
    {
        super(RenderGameOverlayEvent.ElementType.HEALTH, offsetType);
    }

    @Override
    public int getWidth(ScaledResolution sr)
    {
        return 90;
    }

    @Override
    public int getHeight(ScaledResolution sr)
    {
        return 18;
    }

    @Override
    public int getBaseX(ScaledResolution sr)
    {
        int j5 = 0;
        int l = sr.getScaledWidth() / 2 - 91;
        int k4 = l + j5 % 10 * 8;

        return k4;
    }

    @Override
    public int getBaseY(ScaledResolution sr)
    {
        int k1 = MathHelper.ceil(Minecraft.getMinecraft().player.getAbsorptionAmount());
        float f = 40;
        int l1 = MathHelper.ceil((f + (float)k1) / 2.0F / 10.0F);
        int i2 = Math.max(10 - (l1 - 2), 3);
        int j1 = sr.getScaledHeight() - 39;
        int j5 = 0;
        int j4 = MathHelper.ceil((float)(j5 + 1) / 10.0F) - 1;
        int l4 = j1 - j4 * i2;

        return l4;
    }
}
