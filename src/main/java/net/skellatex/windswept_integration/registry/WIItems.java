package net.skellatex.windswept_integration.registry;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skellatex.windswept_integration.WindsweptIntegration;

public class WIItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WindsweptIntegration.MOD_ID);

    public static final DeferredItem<Item> ELDER_BRUSH = ITEMS.register("elder_brush", () -> new BrushItem(new Item.Properties().durability(128)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}