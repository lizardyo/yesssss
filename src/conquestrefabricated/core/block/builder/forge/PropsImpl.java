package com.conquestrefabricated.core.block.builder.forge;

import com.conquestrefabricated.core.block.data.BlockData;
import net.minecraftforge.registries.ForgeRegistries;

public class PropsImpl {
   public static void registerItemByPlatform(BlockData data) {
      ForgeRegistries.ITEMS.register(data.getRegistryName(), data.getItem());
   }
}
