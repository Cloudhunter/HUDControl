package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class PotionRenderInfo extends RenderInfo<RenderGameOverlayEvent.ElementType>
{
    public PotionRenderInfo(OffsetType type)
    {
        super(RenderGameOverlayEvent.ElementType.POTION_ICONS, type);
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
