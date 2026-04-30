package fuzs.hotbarslotcycling.common.impl.data.client;

import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.hotbarslotcycling.common.impl.client.handler.CyclingInputHandler;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.addKeyCategory(HotbarSlotCycling.MOD_ID, HotbarSlotCycling.MOD_NAME);
        builder.add(CyclingInputHandler.CYCLE_LEFT_KEY_MAPPING, "Cycle Hotbar Slot Left");
        builder.add(CyclingInputHandler.CYCLE_RIGHT_KEY_MAPPING, "Cycle Hotbar Slot Right");
    }
}
