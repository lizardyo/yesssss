package com.conquestrefabricated.client.models;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class DuplicateDownBakedModel extends ForwardingBakedModel {
   private static final float DOWN_TRANSLATION = -1.0F;
   private static final float BASE_SCALE_FACTOR = 0.01F;

   public DuplicateDownBakedModel(BakedModel baseModel) {
      this.wrapped = baseModel;
   }

   public boolean isVanillaAdapter() {
      return false;
   }

   public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context) {
      context.pushTransform(quad -> true);
      super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
      context.pushTransform(quad -> {
         if (quad.nominalFace() == Direction.DOWN) {
            return false;
         } else {
            this.translateDown(quad);
            return true;
         }
      });
      super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
      context.popTransform();
      context.popTransform();
   }

   public void translateDown(MutableQuadView quad) {
      float scaleFactor = 0.01F;
      float scaleFactor2 = 0.011F;
      float[] xs = new float[4];
      float[] ys = new float[4];
      float[] zs = new float[4];

      for (int i = 0; i < 4; i++) {
         xs[i] = quad.x(i);
         ys[i] = quad.y(i);
         zs[i] = quad.z(i);
      }

      Direction face = quad.nominalFace();
      switch (face) {
         case NORTH:
            xs[1] += 0.01F;
            zs[1] -= 0.01F;
            xs[2] -= 0.011F;
            zs[2] -= 0.011F;
            break;
         case SOUTH:
            xs[1] -= 0.01F;
            zs[1] += 0.01F;
            xs[2] += 0.011F;
            zs[2] += 0.011F;
            break;
         case EAST:
            xs[1] += 0.011F;
            zs[1] += 0.011F;
            xs[2] += 0.01F;
            zs[2] -= 0.01F;
            break;
         case WEST:
            xs[1] -= 0.011F;
            zs[1] -= 0.011F;
            xs[2] -= 0.01F;
            zs[2] += 0.01F;
      }

      for (int i = 0; i < 4; i++) {
         quad.pos(i, xs[i], ys[i] + -1.0F, zs[i]);
      }
   }
}
