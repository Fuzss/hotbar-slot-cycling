package fuzs.hotbarslotcycling.common.impl.config;

import fuzs.puzzleslib.common.api.util.v1.CommonHelper;

import java.util.function.BooleanSupplier;

public enum ModifierKey {
    DISABLED(() -> false),
    NONE(() -> true),
    CONTROL(CommonHelper::hasControlDown),
    SHIFT(CommonHelper::hasShiftDown),
    ALT(CommonHelper::hasAltDown);

    private final BooleanSupplier active;

    ModifierKey(BooleanSupplier active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active.getAsBoolean();
    }

    public boolean isKey() {
        return this == CONTROL || this == SHIFT || this == ALT;
    }
}
