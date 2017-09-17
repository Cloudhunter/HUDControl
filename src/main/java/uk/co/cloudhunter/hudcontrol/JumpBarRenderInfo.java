package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class JumpBarRenderInfo extends RenderInfo<RenderGameOverlayEvent.ElementType>
{
    public JumpBarRenderInfo(OffsetType type)
    {
        super(RenderGameOverlayEvent.ElementType.JUMPBAR, type);
    }

    @Override
    public int getWidth(ScaledResolution sr)
    {
        return 182;
    }

    @Override
    public int getHeight(ScaledResolution sr)
    {
        return 5;
    }

    @Override
    public int getBaseX(ScaledResolution sr)
    {
        int i = sr.getScaledWidth();
        int k1 = i / 2 - 91;
        return k1;
    }

    @Override
    public int getBaseY(ScaledResolution sr)
    {
        int k = sr.getScaledHeight() - 32 + 3;
        return k;
    }
}
