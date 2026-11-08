package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.init.BlockFamilyInit;
import com.google.gson.Gson;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "conquest",
   bus = Bus.FORGE
)
public class BlockFamilyInitEvent {
   private static final Gson GSON = new Gson();

   @SubscribeEvent
   public static void onAddReloadListeners(AddReloadListenerEvent event) {
      event.addListener(BlockFamilyInit::loadBlockFamilies);
   }
}
