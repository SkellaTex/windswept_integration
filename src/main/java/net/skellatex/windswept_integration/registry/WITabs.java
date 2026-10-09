package net.skellatex.windswept_integration.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.skellatex.windswept_integration.WIConfig;
import net.skellatex.windswept_integration.WindsweptIntegration;
import team.recrafted.blastfromthepast.init.ModBlocks;
import team.recrafted.blastfromthepast.init.ModTabs;

import java.util.function.Supplier;

import static net.skellatex.windswept_integration.compat.ModCompat.BFTP_ID;


@EventBusSubscriber(modid = WindsweptIntegration.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class WITabs {

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            if (WIConfig.COMMON.elderBrush.get()) {
                putAfter(event, Items.BRUSH, WIItems.ELDER_BRUSH);
            }
        }

        if (ModList.get().isLoaded(BFTP_ID)) {
            if (event.getTabKey().equals(ModTabs.BLAST_FROM_THE_PAST.getKey())) {
                if (WIConfig.COMMON.chillyMossCarpet.get()) {
                    putAfter(event, ModBlocks.CHILLY_MOSS, WIBlocks.CHILLY_MOSS_CARPET);
                }
            }
        }
    }


    @SafeVarargs
    private static void putAfter(BuildCreativeModeTabContentsEvent event, ItemLike after, Supplier<? extends ItemLike>... supplier) {
        for (int i = supplier.length - 1; i >= 0; i--) {
            ItemLike key = supplier[i].get();
            event.insertAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SafeVarargs
    private static void putBefore(BuildCreativeModeTabContentsEvent event, ItemLike before, Supplier<? extends ItemLike>... supplier) {
        for (Supplier<? extends ItemLike> supplier1 : supplier) {
            ItemLike key = supplier1.get();
            event.insertBefore(new ItemStack(before), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

}
