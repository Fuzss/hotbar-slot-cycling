package fuzs.hotbarslotcycling.common.impl;

import fuzs.hotbarslotcycling.common.impl.config.ClientConfig;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotbarSlotCycling implements ModConstructor {
    public static final String MOD_ID = "hotbarslotcycling";
    public static final String MOD_NAME = "Hotbar Slot Cycling";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).client(ClientConfig.class);

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
