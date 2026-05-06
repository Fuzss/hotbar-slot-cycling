package fuzs.hotbarslotcycling.common.impl.client;

import fuzs.hotbarslotcycling.common.api.v1.client.HotbarCyclingProvider;
import fuzs.hotbarslotcycling.common.api.v1.client.SlotCyclingProvider;
import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.hotbarslotcycling.common.impl.client.handler.CyclingInputHandler;
import fuzs.hotbarslotcycling.common.impl.client.handler.SlotsRendererHandler;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.GuiLayersContext;
import fuzs.puzzleslib.common.api.client.core.v1.context.KeyMappingsContext;
import fuzs.puzzleslib.common.api.client.event.v1.ClientTickEvents;
import fuzs.puzzleslib.common.api.client.event.v1.gui.HotbarScrollingCallback;
import fuzs.puzzleslib.common.api.client.key.v1.KeyActivationContext;
import fuzs.puzzleslib.common.api.core.v1.ModLoaderEnvironment;
import net.minecraft.world.entity.player.Player;

public class HotbarSlotCyclingClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ClientTickEvents.START.register(CyclingInputHandler::onStartClientTick);
        HotbarScrollingCallback.EVENT.register(CyclingInputHandler::onHotbarScrolling);
    }

    @Override
    public void onClientSetup() {
        if (ModLoaderEnvironment.INSTANCE.isDevelopmentEnvironment(HotbarSlotCycling.MOD_ID)) {
            SlotCyclingProvider.registerProvider((Player player) -> {
                return new HotbarCyclingProvider(player.getInventory());
            });
        }
    }

    @Override
    public void onRegisterKeyMappings(KeyMappingsContext context) {
        context.registerKeyMapping(CyclingInputHandler.CYCLE_LEFT_KEY_MAPPING, KeyActivationContext.GAME);
        context.registerKeyMapping(CyclingInputHandler.CYCLE_RIGHT_KEY_MAPPING, KeyActivationContext.GAME);
    }

    @Override
    public void onRegisterGuiLayers(GuiLayersContext context) {
        context.registerGuiLayer(GuiLayersContext.HOTBAR,
                HotbarSlotCycling.id("cycling_slots"),
                SlotsRendererHandler::onAfterRenderGuiLayer);
    }
}
