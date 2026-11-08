package com.conquestrefabricated.client.models;

import com.conquestrefabricated.content.blocks.block.decor.Loom;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.content.blocks.tileentity.loom.LoomBlockEntity;
import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LoomBakedModel extends ForwardingBakedModel {
   private final BakedModel extraModel;
   private final TextureAtlasSprite[] sprites;

   public LoomBakedModel(BakedModel baseModel, BakedModel extraModel, TextureAtlasSprite[] sprites) {
      this.wrapped = baseModel;
      this.sprites = sprites;
      this.extraModel = extraModel;
   }

   public boolean isVanillaAdapter() {
      return false;
   }

   public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context) {
      BlockEntity blockEntity = blockView.getBlockEntity(pos);
      TextureAtlasSprite sprite = this.sprites[0];
      if ((Boolean)state.getValue(Loom.HAS_THREAD)) {
         if (blockEntity != null && blockEntity.getType() == TileEntityTypes.LOOM) {
            String productData = ((LoomBlockEntity)blockEntity).getProduct();
            int textureVariantIndex = this.getTextureVariant(productData);
            sprite = this.sprites[textureVariantIndex];
         }

         List<BakedQuad> quads = this.getQuads(state, Direction.UP, randomSupplier.get());
         if (quads.isEmpty()) {
            quads = this.getQuads(state, null, randomSupplier.get());
            if (quads.isEmpty()) {
               for (Direction dir : Direction.values()) {
                  quads = this.getQuads(state, dir, randomSupplier.get());
                  if (!quads.isEmpty()) {
                     break;
                  }
               }
            }
         }

         if (!quads.isEmpty()) {
            TextureAtlasSprite originalSprite = quads.get(0).getSprite();
            TextureAtlasSprite finalSprite = sprite;
            context.pushTransform(quadx -> {
               float u0 = quadx.u(0);
               float u1 = quadx.u(1);
               float u2 = quadx.u(2);
               float u3 = quadx.u(3);
               float v0 = quadx.v(0);
               float v1 = quadx.v(1);
               float v2 = quadx.v(2);
               float v3 = quadx.v(3);
               float spriteWidth = originalSprite.getU1() - originalSprite.getU0();
               float spriteHeight = originalSprite.getV1() - originalSprite.getV0();
               float nu0 = (u0 - originalSprite.getU0()) / spriteWidth;
               float nu1 = (u1 - originalSprite.getU0()) / spriteWidth;
               float nu2 = (u2 - originalSprite.getU0()) / spriteWidth;
               float nu3 = (u3 - originalSprite.getU0()) / spriteWidth;
               float nv0 = (v0 - originalSprite.getV0()) / spriteHeight;
               float nv1 = (v1 - originalSprite.getV0()) / spriteHeight;
               float nv2 = (v2 - originalSprite.getV0()) / spriteHeight;
               float nv3 = (v3 - originalSprite.getV0()) / spriteHeight;
               quadx.spriteBake(finalSprite, 0);
               float newU0 = finalSprite.getU0() + nu0 * (finalSprite.getU1() - finalSprite.getU0());
               float newU1 = finalSprite.getU0() + nu1 * (finalSprite.getU1() - finalSprite.getU0());
               float newU2 = finalSprite.getU0() + nu2 * (finalSprite.getU1() - finalSprite.getU0());
               float newU3 = finalSprite.getU0() + nu3 * (finalSprite.getU1() - finalSprite.getU0());
               float newV0 = finalSprite.getV0() + nv0 * (finalSprite.getV1() - finalSprite.getV0());
               float newV1 = finalSprite.getV0() + nv1 * (finalSprite.getV1() - finalSprite.getV0());
               float newV2 = finalSprite.getV0() + nv2 * (finalSprite.getV1() - finalSprite.getV0());
               float newV3 = finalSprite.getV0() + nv3 * (finalSprite.getV1() - finalSprite.getV0());
               quadx.uv(0, newU0, newV0);
               quadx.uv(1, newU1, newV1);
               quadx.uv(2, newU2, newV2);
               quadx.uv(3, newU3, newV3);
               return true;
            });
            super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
            context.popTransform();
         } else {
            super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
            context.popTransform();
         }
      }

      context.pushTransform(quadx -> true);
      if (this.extraModel instanceof ForwardingBakedModel forwardingModel) {
         forwardingModel.emitBlockQuads(blockView, state, pos, randomSupplier, context);
      } else {
         QuadEmitter emitter = context.getEmitter();

         for (BakedQuad quad : this.extraModel.getQuads(state, null, randomSupplier.get())) {
            emitter.fromVanilla(quad, null, null);
            emitter.emit();
         }

         for (Direction direction : Direction.values()) {
            for (BakedQuad quad : this.extraModel.getQuads(state, direction, randomSupplier.get())) {
               emitter.fromVanilla(quad, null, direction);
               emitter.emit();
            }
         }
      }

      context.popTransform();
   }

   private int getTextureVariant(String productData) {
      return switch (productData) {
         case "conquest:red_canvas" -> 1;
         case "conquest:black_canvas" -> 2;
         case "conquest:gray_canvas" -> 3;
         case "conquest:light_gray_canvas" -> 4;
         case "conquest:white_canvas" -> 5;
         case "conquest:brown_canvas" -> 6;
         case "conquest:yellow_canvas" -> 7;
         case "conquest:orange_canvas" -> 8;
         case "conquest:pink_canvas" -> 9;
         case "conquest:magenta_canvas" -> 10;
         case "conquest:purple_canvas" -> 11;
         case "conquest:blue_canvas" -> 12;
         case "conquest:light_blue_canvas" -> 13;
         case "conquest:cyan_canvas" -> 14;
         case "conquest:green_canvas" -> 15;
         case "conquest:lime_canvas" -> 16;
         case "conquest:baotuo_rug" -> 17;
         case "conquest:berber_rug" -> 18;
         case "conquest:black_persian_rug" -> 19;
         case "conquest:blue_nain_rug" -> 20;
         case "conquest:brown_oriental_carpet" -> 21;
         case "conquest:celtic_knot_rug" -> 22;
         case "conquest:kashmiri_carpet" -> 23;
         case "conquest:kazakh_rug" -> 24;
         case "conquest:kilim_rug" -> 25;
         case "conquest:nahavand_rug" -> 26;
         case "conquest:red_and_blue_sarouk_rug" -> 27;
         case "conquest:red_oriental_carpet" -> 28;
         case "conquest:red_pazyryk_rug" -> 29;
         case "conquest:shirishabad_rug" -> 30;
         case "conquest:william_morris_rug" -> 31;
         case "conquest:yellow_red_persian_rug" -> 32;
         default -> 0;
      };
   }
}
