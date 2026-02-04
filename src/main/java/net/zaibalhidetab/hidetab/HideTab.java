package net.zaibalhidetab.hidetab;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("hidetab")
public class HideTab {

    public HideTab() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderTabList(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().toString().contains("player_list")) {
            event.setCanceled(true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyPressed(ScreenEvent.KeyPressed.Pre event) {
        if (event.getScreen() instanceof ChatScreen chat) {
            if (event.getKeyCode() == 258) { // 258 это TAB
                try {
                    String currentText = "";
                    for (Object child : chat.children()) {
                        if (child instanceof EditBox editBox) {
                            currentText = editBox.getValue();
                            break;
                        }
                    }
                    if (!currentText.startsWith("/")) {
                        event.setCanceled(true);
                    }
                } catch (Exception e) {
                    event.setCanceled(true);
                }
            }
        }
    }
}