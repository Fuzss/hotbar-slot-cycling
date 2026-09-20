package fuzs.hotbarslotcycling.common.impl.data.client;

import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.hotbarslotcycling.common.impl.client.handler.CyclingInputHandler;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.addKeyCategory(HotbarSlotCycling.MOD_ID, HotbarSlotCycling.MOD_NAME);
        this.add(CyclingInputHandler.CYCLE_LEFT_KEY_MAPPING, "Cycle Hotbar Slot Left");
        this.add(CyclingInputHandler.CYCLE_RIGHT_KEY_MAPPING, "Cycle Hotbar Slot Right");
    }
}
