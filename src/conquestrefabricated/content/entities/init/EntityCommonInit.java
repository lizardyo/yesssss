package com.conquestrefabricated.content.entities.init;

import com.conquestrefabricated.content.entities.EntityTypes;
import com.conquestrefabricated.content.entities.painting.ModPainting;
import com.conquestrefabricated.content.entities.painting.PaintingEntityType;
import java.util.stream.IntStream;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegisterEvent.RegisterHelper;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class EntityCommonInit {
   @SubscribeEvent
   public static void entities(RegisterEvent event) {
      event.register(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), EntityCommonInit::registerPaintings);
   }

   private static void registerPaintings(RegisterHelper<EntityType<?>> entityTypeRegisterHelper) {
      IntStream.range(0, 10).forEach(i -> ModPainting.register("painting" + i));
      EntityTypes.entityTypesInit();
      PaintingEntityType.paintingEntityTypesInit();
      entityTypeRegisterHelper.register("painting", PaintingEntityType.PAINTING);
      entityTypeRegisterHelper.register("seat", EntityTypes.SEAT);
   }
}
