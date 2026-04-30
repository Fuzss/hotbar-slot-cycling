package fuzs.hotbarslotcycling.fabric.impl;

import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class HotbarSlotCyclingFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(HotbarSlotCycling.MOD_ID, HotbarSlotCycling::new);
    }
}
