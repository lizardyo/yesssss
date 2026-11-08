package com.conquestrefabricated.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBinds {
   public static KeyMapping palette;

   public static KeyMapping getPaletteBind() {
      return palette;
   }
}
