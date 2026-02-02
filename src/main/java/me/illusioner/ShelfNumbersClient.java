package me.illusioner;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ShelfNumbersClient.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ShelfNumbersClient.MODID, value = Dist.CLIENT)
public class ShelfNumbersClient {
    public static final String MODID = "shelf_numbers";

    public ShelfNumbersClient(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, ShelfNumbersConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(BlockEntityType.SHELF, context -> new ShelfNumbersRenderer(context));
    }
}
