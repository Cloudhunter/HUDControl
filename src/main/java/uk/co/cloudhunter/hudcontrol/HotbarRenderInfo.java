package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class HotbarRenderInfo extends RenderInfo<ElementType>
{

    public HotbarRenderInfo(OffsetType offset)
    {
        super(ElementType.HEALTH, offset);
    }

    @Override
    public int getWidth(ScaledResolution sr)
    {
        return 240;
    }

    @Override
    public int getHeight(ScaledResolution sr)
    {
        return 22;
    }

    @Override
    public int getBaseX(ScaledResolution sr)
    {
        return (sr.getScaledWidth() / 2) - 120;
    }

    @Override
    public int getBaseY(ScaledResolution sr)
    {
        return sr.getScaledHeight() - 22;
    }
}
