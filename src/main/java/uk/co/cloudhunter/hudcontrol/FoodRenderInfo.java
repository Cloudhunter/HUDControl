package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class FoodRenderInfo extends RenderInfo<RenderGameOverlayEvent.ElementType>
{
    public FoodRenderInfo(OffsetType type)
    {
        super(RenderGameOverlayEvent.ElementType.FOOD, type);
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
        int i1 = sr.getScaledWidth() / 2 + 91;
        int l7 = i1 - 9 * 8 - 9;
        return l7;
    }

    @Override
    public int getBaseY(ScaledResolution sr)
    {
        return sr.getScaledHeight() - 39;

    }
}
