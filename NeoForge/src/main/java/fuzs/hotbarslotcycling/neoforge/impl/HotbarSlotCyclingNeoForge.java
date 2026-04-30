package fuzs.hotbarslotcycling.neoforge.impl;

import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.neoforged.fml.common.Mod;

@Mod(HotbarSlotCycling.MOD_ID)
public class HotbarSlotCyclingNeoForge {

    public HotbarSlotCyclingNeoForge() {
        ModConstructor.construct(HotbarSlotCycling.MOD_ID, HotbarSlotCycling::new);
    }
}
