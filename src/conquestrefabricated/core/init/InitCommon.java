package com.conquestrefabricated.core.init;

import com.conquestrefabricated.core.block.BlockStats;
import com.conquestrefabricated.core.block.data.BlockDataRegistry;
import com.conquestrefabricated.core.init.dev.Environment;
import com.conquestrefabricated.core.item.family.FamilyRegistry;
import com.conquestrefabricated.core.util.cache.Cache;
import com.conquestrefabricated.core.util.log.Log;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class InitCommon {
   @SubscribeEvent
   public static void common(FMLCommonSetupEvent event) {
      BlockStats stats = new BlockStats();
      Log.info("Block Stats:");
      Log.info("(Total) Blocks: {}, States: {}", stats.totalBlocks, stats.totalStates);
      Log.info("(Vanilla) Blocks: {}, States: {}", stats.vanillaBlocks, stats.vanillaStates);
      Log.info("(Conquest) Blocks: {}, States: {}", stats.conquestBlocks, stats.conquestStates);
   }

   @SubscribeEvent
   public static void complete(FMLLoadCompleteEvent event) {
      if (Environment.isProduction()) {
         Log.info("Load complete. Clearing loading caches");
         Cache.clearAll();
         FamilyRegistry.bake();
         BlockDataRegistry.getInstance().dispose();
      }
   }
}
