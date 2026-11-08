package com.conquestrefabricated.content.effects.forge;

import com.conquestrefabricated.content.effects.Effects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class EffectsInit {
   @SubscribeEvent
   public static void init(RegisterEvent event) {
      event.register(
         Keys.MOB_EFFECTS, effectRegistryHelper -> effectRegistryHelper.register(new ResourceLocation("conquest", "custom_slowness"), Effects.CUSTOM_SLOWNESS)
      );
   }
}
