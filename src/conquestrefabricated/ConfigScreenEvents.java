package com.conquestrefabricated;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.client.gui.config.ConquestConfigScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggingOut;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(
   bus = Bus.MOD,
   modid = "conquest",
   value = {Dist.CLIENT}
)
public class ConfigScreenEvents {
   @SubscribeEvent
   public static void clientSetup(FMLClientSetupEvent event) {
      ModLoadingContext.get()
         .registerExtensionPoint(
            ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> new ConquestConfigScreen(screen, ConquestConfig.INSTANCE))
         );
      MinecraftForge.EVENT_BUS.register(ConfigScreenEvents.ForgeEventListener.class);
   }

   public static class ForgeEventListener {
      @SubscribeEvent
      public static void onClientLoggedOut(LoggingOut event) {
         if (RefabricatedMod.localPlantSlowness != null) {
            ConquestConfig.INSTANCE.plantSlowness.set(RefabricatedMod.localPlantSlowness);
            ConquestConfig.INSTANCE.plantBreaking.set(RefabricatedMod.localPlantBreaking);
            ConquestConfig.INSTANCE.passThroughLeaves.set(RefabricatedMod.localPassThroughLeaves);
            ConquestConfig.INSTANCE.save();
            RefabricatedMod.localPlantSlowness = null;
            RefabricatedMod.localPlantBreaking = null;
            RefabricatedMod.localPassThroughLeaves = null;
         }
      }
   }
}
