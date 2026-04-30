package fuzs.hotbarslotcycling.neoforge.impl.client;

import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.hotbarslotcycling.common.impl.client.HotbarSlotCyclingClient;
import fuzs.hotbarslotcycling.common.impl.data.client.ModLanguageProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = HotbarSlotCycling.MOD_ID, dist = Dist.CLIENT)
public class HotbarSlotCyclingNeoForgeClient {

    public HotbarSlotCyclingNeoForgeClient(ModContainer modContainer) {
        ClientModConstructor.construct(HotbarSlotCycling.MOD_ID, HotbarSlotCyclingClient::new);
        DataProviderHelper.registerDataProviders(HotbarSlotCycling.MOD_ID, ModLanguageProvider::new);
    }
}
