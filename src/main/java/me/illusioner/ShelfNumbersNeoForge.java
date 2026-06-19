package me.illusioner;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterPictureInPictureRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ShelfNumbersNeoForge.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ShelfNumbersNeoForge.MODID, value = Dist.CLIENT)
public class ShelfNumbersNeoForge {
    public static final String MODID = "shelf_numbers";

    public ShelfNumbersNeoForge(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (client, parent) -> {
            return new ShelfNumbersConfigScreen(parent);
        });
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        ShelfNumbersMain.initialize();
    }

    @SubscribeEvent
    public static void registerPip(RegisterPictureInPictureRenderersEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        event.register(GuiBlockEntityRenderState.class, () -> {
            return new GuiBlockEntityRenderer(minecraft.getBlockEntityRenderDispatcher(), minecraft.getModelManager());
        });
    }
}
