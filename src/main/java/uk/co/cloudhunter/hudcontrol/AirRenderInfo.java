package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class AirRenderInfo extends RenderInfo<RenderGameOverlayEvent.ElementType>
{
    public AirRenderInfo(OffsetType type)
    {
        super(RenderGameOverlayEvent.ElementType.AIR, type);
    }

    @Override
    public int getWidth(ScaledResolution sr)
    {
        return 0;
    }

    @Override
    public int getHeight(ScaledResolution sr)
    {
        return 0;
    }

    @Override
    public int getBaseX(ScaledResolution sr)
    {
        return 0;
    }

    @Override
    public int getBaseY(ScaledResolution sr)
    {
        return 0;
    }
}
