package com.conquestrefabricated.content.blocks.tileentity.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesImpl {
   public static <T extends BlockEntity> BlockEntityType<T> create(BlockEntitySupplier<T> factory, String name, String... blockNames) {
      Block[] blocks = new Block[blockNames.length];

      for (int i = 0; i < blockNames.length; i++) {
         Block block = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockNames[i]));
         if (block == null || block == Blocks.AIR) {
            throw new IllegalArgumentException("Block " + blockNames[i] + " is not registered correctly");
         }

         blocks[i] = block;
      }

      return Builder.of(factory, blocks).build(null);
   }
}
