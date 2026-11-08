package com.conquestrefabricated.content.entities.init;

import com.conquestrefabricated.content.entities.EntityTypes;
import com.conquestrefabricated.content.entities.painting.PaintingEntityType;
import com.conquestrefabricated.content.entities.painting.render.PaintingRenderer;
import com.conquestrefabricated.content.entities.painting.render.VanillaPaintingRenderer;
import com.conquestrefabricated.content.entities.seat.SeatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class EntityClientInit {
   @SubscribeEvent
   public static void setup(FMLClientSetupEvent event) {
      EntityRenderers.register(EntityType.PAINTING, VanillaPaintingRenderer::new);
      EntityRenderers.register(PaintingEntityType.PAINTING, PaintingRenderer::new);
      EntityRenderers.register(EntityTypes.SEAT, SeatRenderer::new);
   }
}
