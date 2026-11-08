package com.conquestrefabricated.core.client.input.forge;

import com.conquestrefabricated.core.client.input.BindListener;
import com.conquestrefabricated.core.client.input.EventBinding;
import com.mojang.blaze3d.platform.InputConstants;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class Bindings {
   private static final List<EventBinding> bindings = new LinkedList<>();

   public static KeyMapping createBasic(String description, String input, String category) {
      return new KeyMapping(description, InputConstants.getKey(input).getValue(), category);
   }

   public static EventBinding create(String description, String input, String category) {
      EventBinding binding = new EventBinding(description, InputConstants.getKey(input), category);
      bindings.add(binding);
      return binding;
   }

   public static EventBinding create(String description, String input, String category, BindListener listener) {
      EventBinding binding = new EventBinding(description, InputConstants.getKey(input), category);
      binding.addListener(listener);
      bindings.add(binding);
      return binding;
   }

   @SubscribeEvent
   public static void tick(ClientTickEvent event) {
      for (EventBinding binding : bindings) {
         if (binding.checkPressed()) {
            return;
         }

         binding.checkHeld();
      }
   }
}
