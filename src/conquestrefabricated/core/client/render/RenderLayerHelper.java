package com.conquestrefabricated.core.client.render;

import com.conquestrefabricated.core.client.render.forge.RenderLayerHelperImpl;
import com.conquestrefabricated.core.util.RenderLayer;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayerHelper {
   @ExpectPlatform
   @Transformed
   public static void register(Block block, RenderLayer layer) {
      RenderLayerHelperImpl.register(block, layer);
   }

   @ExpectPlatform
   @Transformed
   public static void register(Fluid fluid, RenderLayer layer) {
      RenderLayerHelperImpl.register(fluid, layer);
   }

   public static RenderType getType(RenderLayer layer) {
      switch (layer) {
         case SOLID:
            return RenderType.solid();
         case CUTOUT:
            return RenderType.cutout();
         case TRANSLUCENT:
            return RenderType.translucent();
         case CUTOUT_MIPPED_SOLID:
         case CUTOUT_MIPPED:
            return RenderType.cutoutMipped();
         default:
            return null;
      }
   }
}
