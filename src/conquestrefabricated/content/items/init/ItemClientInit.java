package com.conquestrefabricated.content.items.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemClientInit {
   public static void setup(Minecraft client) {
      registerModel("conquest:painting", "conquest:painting", client);
      registerModel("conquest:vanilla_painting", "minecraft:painting", client);
   }

   private static void registerModel(String item, String model, Minecraft client) {
      registerModel(new ResourceLocation(item), model, client);
   }

   private static void registerModel(ResourceLocation item, String model, Minecraft client) {
      registerModel((Item)BuiltInRegistries.ITEM.get(item), model, client);
   }

   private static void registerModel(Item item, String model, Minecraft client) {
      ModelResourceLocation modelLocation = new ModelResourceLocation(new ResourceLocation(model), "inventory");
      client.getItemRenderer().getItemModelShaper().register(item, modelLocation);
   }
}
