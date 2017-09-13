package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.vecmath.Vector2f;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
    private boolean isHeld;
    private RenderInfo focused;


    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        ScaledResolution sr = new ScaledResolution(this.mc);
        isHeld = true;
        focused = null;

        for(Map.Entry<Object, RenderInfo> entry : HUDControl.renderInfos.entrySet()) {
            RenderInfo info = entry.getValue();
            if (!info.isClicked(mouseX, mouseY, sr)) continue;
            focused = info;
            originalState = new Vector2f(info.getXOffset(sr), info.getYOffset(sr));
            clickPos = new Vector2f(mouseX, mouseY);
            break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (!isHeld || focused == null) return;
        Vector2f changed = new Vector2f(clickPos);
        changed.sub(new Vector2f(mouseX, mouseY));
        Vector2f renderRepl = new Vector2f(originalState);
        renderRepl.sub(changed);

        focused.setXOffset(renderRepl.getX());
        focused.setYOffset(renderRepl.getY());
    }

    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        isHeld = false;
        originalState = null;
        clickPos = null;
    }


}
