package fuzs.hotbarslotcycling.common.impl.client.handler;

import com.mojang.blaze3d.platform.InputConstants;
import fuzs.hotbarslotcycling.common.api.v1.client.ItemCyclingProvider;
import fuzs.hotbarslotcycling.common.api.v1.client.SlotCyclingProvider;
import fuzs.hotbarslotcycling.common.impl.HotbarSlotCycling;
import fuzs.hotbarslotcycling.common.impl.config.ClientConfig;
import fuzs.hotbarslotcycling.common.impl.config.ModifierKey;
import fuzs.puzzleslib.common.api.client.key.v1.KeyMappingHelper;
import fuzs.puzzleslib.common.api.event.v1.core.EventResultHolder;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class CyclingInputHandler {
    public static final KeyMapping CYCLE_LEFT_KEY_MAPPING = KeyMappingHelper.registerKeyMapping(HotbarSlotCycling.id(
            "cycle_left"), InputConstants.KEY_H);
    public static final KeyMapping CYCLE_RIGHT_KEY_MAPPING = KeyMappingHelper.registerKeyMapping(HotbarSlotCycling.id(
            "cycle_right"), InputConstants.KEY_J);
    private static final int DEFAULT_SLOTS_DISPLAY_TICKS = 15;

    private static int slotsDisplayTicks;
    private static int globalPopTime;

    public static EventResultHolder<Integer> onHotbarScrolling(Inventory inventory, int oldSlot, int newSlot, double scrollAmountX, double scrollAmountY) {
        if (HotbarSlotCycling.CONFIG.get(ClientConfig.class).scrollingModifierKey.isActive()) {
            int slotDelta = newSlot - oldSlot;
            if (slotDelta != 0) {
                Player player = Minecraft.getInstance().player;
                if (slotDelta > 0 == HotbarSlotCycling.CONFIG.get(ClientConfig.class).invertScrolling) {
                    if (cycleSlot(player, SlotCyclingProvider::cycleSlotBackward)) {
                        return EventResultHolder.interrupt(-1);
                    }
                } else {
                    if (cycleSlot(player, SlotCyclingProvider::cycleSlotForward)) {
                        return EventResultHolder.interrupt(-1);
                    }
                }
            }
        }

        return EventResultHolder.pass();
    }

    public static void onStartClientTick(Minecraft minecraft) {
        if (slotsDisplayTicks > 0) {
            slotsDisplayTicks--;
        }

        if (globalPopTime > 0) {
            globalPopTime--;
        }

        if (minecraft.player != null && !minecraft.player.isSpectator()) {
            if (minecraft.getOverlay() == null && minecraft.screen == null) {
                handleModKeybinds(minecraft.player);
                handleHotbarKeybinds(minecraft.player, minecraft.options);
            }

            if (HotbarSlotCycling.CONFIG.get(ClientConfig.class).scrollingModifierKey.isActive()) {
                slotsDisplayTicks = DEFAULT_SLOTS_DISPLAY_TICKS;
            }
        }
    }

    private static void handleModKeybinds(Player player) {
        while (CYCLE_LEFT_KEY_MAPPING.consumeClick()) {
            cycleSlot(player, SlotCyclingProvider::cycleSlotBackward);
        }

        while (CYCLE_RIGHT_KEY_MAPPING.consumeClick()) {
            cycleSlot(player, SlotCyclingProvider::cycleSlotForward);
        }
    }

    private static void handleHotbarKeybinds(Player player, Options options) {
        if (!HotbarSlotCycling.CONFIG.get(ClientConfig.class).doublePressHotbarKey) {
            return;
        }

        boolean saveHotbarActivatorDown = options.keySaveHotbarActivator.isDown();
        boolean loadHotbarActivatorDown = options.keyLoadHotbarActivator.isDown();
        if (!player.isCreative() || !loadHotbarActivatorDown && !saveHotbarActivatorDown) {
            ModifierKey scrollingModifierKey = HotbarSlotCycling.CONFIG.get(ClientConfig.class).scrollingModifierKey;
            boolean forward = !scrollingModifierKey.isKey() || !scrollingModifierKey.isActive();
            for (int i = 0; i < options.keyHotbarSlots.length; i++) {
                while (i == player.getInventory().getSelectedSlot() && options.keyHotbarSlots[i].consumeClick()) {
                    cycleSlot(player,
                            forward ? SlotCyclingProvider::cycleSlotForward : SlotCyclingProvider::cycleSlotBackward);
                }
            }
        }
    }

    private static boolean cycleSlot(Player player, Predicate<SlotCyclingProvider> cycleAction) {
        SlotCyclingProvider provider = SlotCyclingProvider.getProvider(player);
        if (provider != null && cycleAction.test(provider)) {
            slotsDisplayTicks = DEFAULT_SLOTS_DISPLAY_TICKS;
            globalPopTime = 5;
            player.stopUsingItem();
            if (provider instanceof ItemCyclingProvider itemProvider) {
                clearItemRendererInHand(itemProvider.interactionHand());
            }

            return true;
        }

        return false;
    }

    private static void clearItemRendererInHand(InteractionHand interactionHand) {
        // force the reequip animation for the new held item
        ItemInHandRenderer itemInHandRenderer = Minecraft.getInstance().gameRenderer.itemInHandRenderer;
        if (interactionHand == InteractionHand.OFF_HAND) {
            itemInHandRenderer.offHandItem = ItemStack.EMPTY;
        } else {
            itemInHandRenderer.mainHandItem = ItemStack.EMPTY;
        }
    }

    public static int getSlotsDisplayTicks() {
        return slotsDisplayTicks;
    }

    public static int getGlobalPopTime() {
        return globalPopTime;
    }
}
