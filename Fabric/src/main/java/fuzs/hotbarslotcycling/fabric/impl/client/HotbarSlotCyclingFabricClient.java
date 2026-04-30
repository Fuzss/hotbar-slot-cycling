package fuzs.hotbarslotcycling.fabric.impl.client;

import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.hotbarslotcycling.common.impl.client.HotbarSlotCyclingClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class HotbarSlotCyclingFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(HotbarSlotCycling.MOD_ID, HotbarSlotCyclingClient::new);
    }
}
