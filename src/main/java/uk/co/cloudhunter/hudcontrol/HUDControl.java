package uk.co.cloudhunter.hudcontrol;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
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

@Mod(modid = HUDControl.MODID, version = HUDControl.VERSION, clientSideOnly = true)
public class HUDControl
{
    public static final String MODID = "hudcontrol";
    public static final String VERSION = "1.0";

    public static final KeyBinding binding = new KeyBinding("key.guichange", Keyboard.KEY_LCONTROL, "key.category.gui");

    public static HashMap<Object, RenderInfo> renderInfos = new HashMap<Object, RenderInfo>() {{
        this.put(RenderGameOverlayEvent.ElementType.HOTBAR, new HotbarRenderInfo(RenderInfo.OffsetType.TOPLEFT));
        this.put(RenderGameOverlayEvent.ElementType.HEALTH, new HealthRenderInfo(RenderInfo.OffsetType.TOPLEFT));
    }};

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doBaseTranslation(RenderInfo info, ScaledResolution sr, boolean invert) {
        RenderInfo.OffsetType offset = info.getOffsetType();
        int x = info.getBaseX(sr);
        int y = info.getBaseY(sr);
        x = -x;
        y = -y;
        switch (offset)
        {
            default:
            case TOPLEFT:
                break;
            case TOPRIGHT:
                x += sr.getScaledWidth() - info.getWidth(sr);
                break;
            case BOTTOMRIGHT:
                x += sr.getScaledWidth() - info.getWidth(sr);
            case BOTTOMLEFT:
                y += sr.getScaledHeight() - info.getHeight(sr);
                break;
            case ORIGINAL:
                x = 0;
                y = 0;
        }

        if (invert)
        {
            x = -x;
            y = -y;
        }

        GlStateManager.translate(x, y, 0F);
    }

    private void doTranslation(Object type, ScaledResolution sr, boolean post) {
        RenderInfo info = renderInfos.get(type);
        if (info == null) return;

        doBaseTranslation(info, sr, post);

        Vector2f translate;

        if (!post)
            translate = new Vector2f(info.getXOffset(sr), info.getYOffset(sr));
        else
            translate = new Vector2f(-info.getXOffset(sr), -info.getYOffset(sr));

        GlStateManager.translate(translate.x, translate.y, 0F);
    }

    @SubscribeEvent
    public void HUDRender(RenderGameOverlayEvent.Pre e)
    {
        doTranslation(e.getType(), e.getResolution(), false);
    }

    @SubscribeEvent
    public void HUDRender(RenderGameOverlayEvent.Post e)
    {
        doTranslation(e.getType(), e.getResolution(), true);
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
