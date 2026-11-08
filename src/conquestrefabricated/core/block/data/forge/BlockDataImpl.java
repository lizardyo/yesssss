package com.conquestrefabricated.core.block.data.forge;

import com.conquestrefabricated.core.block.data.BlockData;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockDataImpl {
   public static void registerBlock(BlockData blockData) {
      ForgeRegistries.BLOCKS.register(blockData.getRegistryName(), blockData.getBlock());
   }
}
