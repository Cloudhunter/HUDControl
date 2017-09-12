package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.vecmath.Vector2f;
import java.io.IOException;

public class GuiTransparent extends GuiScreen
{

    @Override
    public void drawDefaultBackground() {
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private Vector2f originalState;
    private Vector2f clickPos;
    private boolean hotbarClicked;

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        ScaledResolution sr = new ScaledResolution(this.mc);

        int x = (sr.getScaledWidth() / 2) - 91 - 29;
        int y = sr.getScaledHeight() - 22;

        Vector2f offset = HUDControl.translateAmount.get(RenderGameOverlayEvent.ElementType.HOTBAR);

        x += offset.x;
        y += offset.y;

        int width = 182 + 29 + 29;
        int height = 22;

        if (mouseY >= y && mouseY <= y + height && mouseX >= x && mouseX <= x + width)
        {
            originalState = new Vector2f(HUDControl.translateAmount.get(RenderGameOverlayEvent.ElementType.HOTBAR));
            clickPos = new Vector2f(mouseX, mouseY);
            hotbarClicked = true;
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (!hotbarClicked) return;
        Vector2f changed = new Vector2f(clickPos);
        changed.sub(new Vector2f(mouseX, mouseY));
        Vector2f renderRepl = new Vector2f(originalState);
        renderRepl.sub(changed);
        HUDControl.translateAmount.put(RenderGameOverlayEvent.ElementType.HOTBAR, renderRepl);
    }

    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        hotbarClicked = false;
        originalState = null;
        clickPos = null;
    }


}
