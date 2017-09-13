package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.ScaledResolution;

public abstract class RenderInfo<E>
{
    private E renderElement;
    private OffsetType offsetType;
    private float xOffset;
    private float yOffset;

    public RenderInfo(E element, OffsetType type) {
        renderElement = element;
        offsetType = type;
    }

    public abstract int getWidth(ScaledResolution sr);

    public abstract int getHeight(ScaledResolution sr);

    public abstract int getBaseX(ScaledResolution sr);

    public abstract int getBaseY(ScaledResolution sr);

    public float getXOffset(ScaledResolution sr)
    {
        return xOffset;
    }

    public float getYOffset(ScaledResolution sr)
    {
        return yOffset;
    }

    public void setXOffset(float x)
    {
        xOffset = x;
    }

    public void setYOffset(float y)
    {
        yOffset = y;
    }

    public boolean isClicked(int mouseX, int mouseY, ScaledResolution sr)
    {
        int x, y;
        switch (getOffsetType())
        {
            default:
            case TOPLEFT:
                x = 0;
                y = 0;
                break;
            case TOPRIGHT:
                x = sr.getScaledWidth() - getWidth(sr);
                y = 0;
                break;
            case BOTTOMRIGHT:
                x = sr.getScaledWidth() - getWidth(sr);
                y = sr.getScaledHeight() - getHeight(sr);
            case BOTTOMLEFT:
                x = 0;
                y = sr.getScaledHeight() - getHeight(sr);
                break;
            case ORIGINAL:
                x = getBaseY(sr);
                y = getBaseX(sr);
        }

        x += getXOffset(sr);
        y += getYOffset(sr);

        return mouseX >= x && mouseX <= x + getWidth(sr) && mouseY >= y && mouseY <= y + getHeight(sr);
    }

    public OffsetType getOffsetType()
    {
        return offsetType;
    }

    enum OffsetType {
        CENTRE,
        TOPLEFT,
        TOPRIGHT,
        BOTTOMLEFT,
        BOTTOMRIGHT,
        ORIGINAL
    }
}
