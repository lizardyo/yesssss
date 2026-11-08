package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.init.BlockRegistrar;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.core.util.log.Log;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class BlockRegistrarEvent {
   @SubscribeEvent
   public static void blocks(RegisterEvent event) {
      event.register(Keys.BLOCKS, blockRegisterHelper -> {
         Log.info("Registering blocks");
         BlockRegistrar.blocks();
         LiliesInit.registerBlocks();
         ScaffoldingInit.registerBlocks();
      });
      event.register(Keys.BLOCK_ENTITY_TYPES, blockEntityTypeRegisterHelper -> {
         Log.info("Registering block entities");
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:loom"), TileEntityTypes.LOOM);
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:campfire"), TileEntityTypes.CAMPFIRE);
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:enchanter"), TileEntityTypes.ENCHANTING_TABLE);
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:oven"), TileEntityTypes.FURNACE);
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:animal"), TileEntityTypes.ANIMAL);
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:seat"), TileEntityTypes.SEAT);
         blockEntityTypeRegisterHelper.register(new ResourceLocation("conquest:kiln"), TileEntityTypes.KILN);
      });
   }

   @SubscribeEvent
   public static void items(RegisterEvent event) {
      event.register(Keys.ITEMS, itemRegisterHelper -> {
         Log.info("Registering block items");
         LiliesInit.registerItems();
         ScaffoldingInit.registerItems();
      });
   }
}
