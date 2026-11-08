package com.conquestrefabricated.core.init;

import com.conquestrefabricated.core.block.data.BlockData;
import java.lang.invoke.StringConcatFactory;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class Registrar {
   public static <V extends RegistryObject<V>> void register(BlockData data, V v, IForgeRegistry<V> registry) {
      try {
         registry.register(data.getRegistryName(), v);
      } catch (Throwable var10) {
         String type = registry.getRegistryName().getPath();
         String blockType = data.getBlock().getClass().getName();
         String blockName = data.getBlockName().toString();
         String itemType = data.getItem().getClass().getName();
         String itemName = StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(data.getItem().getDescription().toString());
         String message = type + "(" + blockType + "[" + blockName + "] / " + itemType + "[" + itemName + "]) ";
         throw new Registrar.RegistrationException(message, var10);
      }
   }

   private static class RegistrationException extends RuntimeException {
      private RegistrationException(String message, Throwable cause) {
         super(message, cause);
      }
   }
}
