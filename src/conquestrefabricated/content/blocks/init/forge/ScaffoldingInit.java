package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.block.decor.Scaffolding;
import com.conquestrefabricated.content.items.item.ScaffoldingItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.ForgeRegistries;

public class ScaffoldingInit {
   public static final Block METAL_SCAFFOLDING = createBlock("metal_scaffolding", SoundType.METAL);
   public static final Block WOOD_SCAFFOLDING = createBlock("wood_scaffolding", SoundType.WOOD);

   public static void registerBlocks() {
      ForgeRegistries.BLOCKS.register("conquest:metal_scaffolding", METAL_SCAFFOLDING);
      ForgeRegistries.BLOCKS.register("conquest:wood_scaffolding", WOOD_SCAFFOLDING);
   }

   public static void registerItems() {
      ForgeRegistries.ITEMS.register("conquest:metal_scaffolding", createItem(METAL_SCAFFOLDING));
      ForgeRegistries.ITEMS.register("conquest:wood_scaffolding", createItem(WOOD_SCAFFOLDING));
   }

   private static Scaffolding createBlock(String name, SoundType soundType) {
      return new Scaffolding(Properties.of().strength(0.5F).sound(soundType));
   }

   private static Item createItem(Block block) {
      Item item = new ScaffoldingItem(block, new net.minecraft.world.item.Item.Properties());
      return item;
   }
}
