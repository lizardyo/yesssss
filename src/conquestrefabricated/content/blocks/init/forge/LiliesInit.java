package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.block.plants.LilyPad;
import com.conquestrefabricated.content.blocks.block.plants.LilyPadToggle2;
import com.conquestrefabricated.content.items.item.LilypadItem;
import com.conquestrefabricated.core.init.Context;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.ForgeRegistries;

public class LiliesInit {
   public static final Block WHITE_WATER_LILIES = createBlock("white_water_lilies", SoundType.GRASS, false);
   public static final Block BIG_WATER_LILIES = createBlock("big_water_lilies", SoundType.GRASS, false);
   public static final Block DUCKWEED = createLilyToggle2Block("duckweed", SoundType.GRASS, false);
   public static final Block FLOATING_ICE = createBlock("floating_ice", SoundType.GLASS, false);

   public static void registerBlocks() {
      ForgeRegistries.BLOCKS.register(Context.getInstance().getNamespace() + ":white_water_lilies", WHITE_WATER_LILIES);
      ForgeRegistries.BLOCKS.register(Context.getInstance().getNamespace() + ":big_water_lilies", BIG_WATER_LILIES);
      ForgeRegistries.BLOCKS.register(Context.getInstance().getNamespace() + ":duckweed", DUCKWEED);
      ForgeRegistries.BLOCKS.register(Context.getInstance().getNamespace() + ":floating_ice", FLOATING_ICE);
   }

   public static void registerItems() {
      ForgeRegistries.ITEMS.register(Context.getInstance().getNamespace() + ":white_water_lilies", createItem(WHITE_WATER_LILIES));
      ForgeRegistries.ITEMS.register(Context.getInstance().getNamespace() + ":big_water_lilies", createItem(BIG_WATER_LILIES));
      ForgeRegistries.ITEMS.register(Context.getInstance().getNamespace() + ":duckweed", createItem(DUCKWEED));
      ForgeRegistries.ITEMS.register(Context.getInstance().getNamespace() + ":floating_ice", createItem(FLOATING_ICE));
   }

   private static LilyPadToggle2 createLilyToggle2Block(String name, SoundType soundType, boolean hasCollision) {
      Properties props = Properties.of().strength(0.5F).sound(soundType);
      if (!hasCollision) {
         props = props.noCollission();
      }

      return new LilyPadToggle2(props);
   }

   private static LilyPad createBlock(String name, SoundType soundType, boolean hasCollision) {
      Properties props = Properties.of().strength(0.5F).sound(soundType);
      if (!hasCollision) {
         props = props.noCollission();
      }

      return new LilyPad(props);
   }

   private static Item createItem(Block block) {
      Item item = new LilypadItem(block, new net.minecraft.world.item.Item.Properties());
      return item;
   }
}
