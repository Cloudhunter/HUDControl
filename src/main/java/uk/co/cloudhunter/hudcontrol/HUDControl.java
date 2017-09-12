package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

import javax.vecmath.Vector2f;
import java.util.HashMap;

import static uk.co.cloudhunter.hudcontrol.HUDControl.OffsetType.BOTTOMRIGHT;
import static uk.co.cloudhunter.hudcontrol.HUDControl.OffsetType.TOPLEFT;
import static uk.co.cloudhunter.hudcontrol.HUDControl.OffsetType.TOPRIGHT;

@Mod(modid = HUDControl.MODID, version = HUDControl.VERSION, clientSideOnly = true)
public class HUDControl
{
    public static final String MODID = "hudcontrol";
    public static final String VERSION = "1.0";

    public static final KeyBinding binding = new KeyBinding("key.guichange", Keyboard.KEY_LCONTROL, "key.category.gui");

    public static HashMap<RenderGameOverlayEvent.ElementType, Vector2f> translateAmount = new HashMap<RenderGameOverlayEvent.ElementType, Vector2f>() {{
        this.put(RenderGameOverlayEvent.ElementType.HOTBAR, new Vector2f(0f, 0f));
    }};

    public static HashMap<RenderGameOverlayEvent.ElementType, OffsetType> offsetType = new HashMap<RenderGameOverlayEvent.ElementType, OffsetType>() {{
        this.put(RenderGameOverlayEvent.ElementType.HOTBAR, BOTTOMRIGHT);
    }};

    enum OffsetType {
        CENTRE,
        TOPLEFT,
        TOPRIGHT,
        BOTTOMLEFT,
        BOTTOMRIGHT,
        ORIGINAL
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void HUDRender(RenderGameOverlayEvent.Pre e)
    {
        Vector2f translate = translateAmount.get(e.getType());
        OffsetType offset = offsetType.get(e.getType());
        if (offset == null)
            return;
        ScaledResolution sr = e.getResolution();
        int x = (sr.getScaledWidth() / 2) - 91 - 29;
        int y = sr.getScaledHeight() - 22;
        x = -x;
        y = -y;
        switch (offset)
        {
            default:
            case TOPLEFT:
                break;
            case TOPRIGHT:
                x += sr.getScaledWidth() - (182 + 29 + 29);
                break;
            case BOTTOMRIGHT:
                x += sr.getScaledWidth() - (182 + 29 + 29);
            case BOTTOMLEFT:
                y += sr.getScaledHeight() - 22;
                break;
            case ORIGINAL:
                x = 0;
                y = 0;
        }
        GlStateManager.translate(x, y, 0F);
        if (translate != null)
        {
            GlStateManager.translate(translate.x, translate.y, 0F);
        }

    }

    @SubscribeEvent
    public void HUDRender(RenderGameOverlayEvent.Post e)
    {
        Vector2f translate = translateAmount.get(e.getType());
        OffsetType offset = offsetType.get(e.getType());
        if (offset == null)
            return;
        ScaledResolution sr = e.getResolution();
        int x = (sr.getScaledWidth() / 2) - 91 - 29;
        int y = sr.getScaledHeight() - 22;
        x = -x;
        y = -y;
        switch (offset)
        {
            default:
            case TOPLEFT:
                break;
            case TOPRIGHT:
                x += sr.getScaledWidth() - (182 + 29 + 29);
                break;
            case BOTTOMRIGHT:
                x += sr.getScaledWidth() - (182 + 29 + 29);
            case BOTTOMLEFT:
                y += sr.getScaledHeight() - 22;
                break;
            case ORIGINAL:
                x = 0;
                y = 0;
        }
        GlStateManager.translate(-x, -y, 0F);
        if (translate != null)
        {
            GlStateManager.translate(-translate.x, -translate.y, 0F);
        }
    }

    private static boolean ourStuffHandling = true;

    @SubscribeEvent()
    public void update(TickEvent.PlayerTickEvent e)
    {
        if (e.side != Side.CLIENT || e.phase == TickEvent.Phase.END || Minecraft.getMinecraft().currentScreen != null) return;

        if (e.type == TickEvent.Type.PLAYER)
        {
            if (binding.isPressed()) {
                Minecraft mc = Minecraft.getMinecraft();
                if (ourStuffHandling)
                {
                    ourStuffHandling = false;
                    mc.displayGuiScreen(null);
                }
                else
                {
                    ourStuffHandling = true;
                    mc.displayGuiScreen(new GuiTransparent());
                }
            }
        }
    }
}
