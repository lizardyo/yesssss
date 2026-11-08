package com.conquestrefabricated.mixin;

import com.conquestrefabricated.client.models.DuplicateDownUnbakedModel;
import com.conquestrefabricated.client.models.LoomUnbakedModel;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ModelBakery.class})
public class TranslatedModelLoaderMixin {
   @Final
   private static final ModelResourceLocation WARP_WEIGHTED_LOOM = new ModelResourceLocation(ResourceLocation.tryBuild("conquest", "warp_weighted_loom"), "");
   @Final
   private static final ModelResourceLocation SIMPLE_WARP_WEIGHTED_LOOM = new ModelResourceLocation(
      ResourceLocation.tryBuild("conquest", "simple_warp_weighted_loom"), ""
   );
   @Final
   private static final ModelResourceLocation TWO_BEAM_LOOM = new ModelResourceLocation(ResourceLocation.tryBuild("conquest", "two_beam_loom"), "");
   @Final
   private static final ModelResourceLocation STANDING_TWO_BEAM_LOOM = new ModelResourceLocation(
      ResourceLocation.tryBuild("conquest", "standing_two_beam_loom"), ""
   );
   @Shadow
   @Final
   private Map<ResourceLocation, UnbakedModel> unbakedCache;
   @Shadow
   @Final
   private Set<ResourceLocation> loadingStack;

   @Shadow
   public UnbakedModel getModel(ResourceLocation id) {
      throw new AssertionError();
   }

   @Inject(
      method = {"putModel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onPutModel(ResourceLocation id, UnbakedModel unbakedModel, CallbackInfo ci) {
      if (id instanceof ModelResourceLocation modelId) {
         if (!modelId.getVariant().equals("inventory")
            && (modelId.getVariant().contains("offset_toggle=true") || modelId.getVariant().contains("extension_toggle=true"))) {
            ResourceLocation blockId = new ResourceLocation(modelId.getNamespace(), modelId.getPath());
            Block block = (Block)BuiltInRegistries.BLOCK.get(blockId);
            SpecialOffset specialOffset = block.getClass().getAnnotation(SpecialOffset.class);
            if (specialOffset != null) {
               SpecialOffsetType specialOffsetType = specialOffset.offsetType();
               switch (specialOffsetType) {
                  case DUPLICATE_DOWN:
                     UnbakedModel newModel = new DuplicateDownUnbakedModel(unbakedModel);
                     this.unbakedCache.put(id, newModel);
                     this.loadingStack.addAll(newModel.getDependencies());
                     ci.cancel();
               }
            }
         }

         String idString = id.toString();
         if (id.getPath().contains(SIMPLE_WARP_WEIGHTED_LOOM.getPath()) && idString.contains("has_thread=true")) {
            this.handleLoomModel(idString, "simple_warp_weighted_loom", id);
            ci.cancel();
         } else if (id.getPath().contains(STANDING_TWO_BEAM_LOOM.getPath()) && idString.contains("has_thread=true")) {
            this.handleLoomModel(idString, "standing_two_beam_loom", id);
            ci.cancel();
         } else if (id.getPath().contains(TWO_BEAM_LOOM.getPath()) && idString.contains("has_thread=true")) {
            this.handleLoomModel(idString, "two_beam_loom", id);
            ci.cancel();
         } else if (id.getPath().contains(WARP_WEIGHTED_LOOM.getPath()) && idString.contains("has_thread=true")) {
            this.handleLoomModel(idString, "warp_weighted_loom", id);
            ci.cancel();
         }
      }
   }

   private void handleLoomModel(String idString, String loomType, ResourceLocation id) {
      UnbakedModel baseModelSmall = this.getModel(new ResourceLocation("conquest", "block/looms/" + loomType + "_weave_small"));
      UnbakedModel extraModelSmall = this.getModel(new ResourceLocation("conquest", "block/looms/" + loomType + "_threaded_small"));
      UnbakedModel baseModelSmall45 = this.getModel(new ResourceLocation("conquest", "block/looms/" + loomType + "_weave_small_45"));
      UnbakedModel extraModelSmall45 = this.getModel(new ResourceLocation("conquest", "block/looms/" + loomType + "_threaded_small_45"));
      ResourceLocation baseModelId = new ResourceLocation("conquest", "block/looms/" + loomType + "_weave");
      this.loadingStack.add(baseModelId);
      UnbakedModel baseModel = this.unbakedCache.get(baseModelId);
      ResourceLocation extraModelId = new ResourceLocation("conquest", "block/looms/" + loomType + "_threaded");
      this.loadingStack.add(extraModelId);
      UnbakedModel extraModel = this.unbakedCache.get(extraModelId);
      ResourceLocation baseModel45Id = new ResourceLocation("conquest", "block/looms/" + loomType + "_weave_45");
      this.loadingStack.add(baseModel45Id);
      UnbakedModel baseModel45 = this.unbakedCache.get(baseModel45Id);
      ResourceLocation extraModel45Id = new ResourceLocation("conquest", "block/looms/" + loomType + "_threaded_45");
      this.loadingStack.add(extraModel45Id);
      UnbakedModel extraModel45 = this.unbakedCache.get(extraModel45Id);
      ResourceLocation baseModelLargeId = new ResourceLocation("conquest", "block/looms/" + loomType + "_weave_large");
      this.loadingStack.add(baseModelLargeId);
      UnbakedModel baseModelLarge = this.unbakedCache.get(baseModelLargeId);
      ResourceLocation extraModelLargeId = new ResourceLocation("conquest", "block/looms/" + loomType + "_threaded_large");
      this.loadingStack.add(extraModelLargeId);
      UnbakedModel extraModelLarge = this.unbakedCache.get(extraModelLargeId);
      ResourceLocation baseModelLarge45Id = new ResourceLocation("conquest", "block/looms/" + loomType + "_weave_large_45");
      this.loadingStack.add(baseModelLarge45Id);
      UnbakedModel baseModelLarge45 = this.unbakedCache.get(baseModelLarge45Id);
      ResourceLocation extraModelLarge45Id = new ResourceLocation("conquest", "block/looms/" + loomType + "_threaded_large_45");
      this.loadingStack.add(extraModelLarge45Id);
      UnbakedModel extraModelLarge45 = this.unbakedCache.get(extraModelLarge45Id);
      if (idString.contains("size=1")) {
         if (idString.contains("position=1")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y90);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y180);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y270);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=2")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall45, extraModelSmall45, "small", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall45, extraModelSmall45, "small", BlockModelRotation.X0_Y90);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall45, extraModelSmall45, "small", BlockModelRotation.X0_Y180);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall45, extraModelSmall45, "small", BlockModelRotation.X0_Y270);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall45, extraModelSmall45, "small", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=3")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y0, new Vector3f(0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y90, new Vector3f(0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y180, new Vector3f(0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y270, new Vector3f(0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=4")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y0, new Vector3f(-0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y90, new Vector3f(-0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y180, new Vector3f(-0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y270, new Vector3f(-0.5F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelSmall, extraModelSmall, "small", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         }
      } else if (idString.contains("size=3")) {
         if (idString.contains("position=1")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y90);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y180);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y270);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=2")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge45, extraModelLarge45, "large", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge45, extraModelLarge45, "large", BlockModelRotation.X0_Y90);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge45, extraModelLarge45, "large", BlockModelRotation.X0_Y180);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge45, extraModelLarge45, "large", BlockModelRotation.X0_Y270);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge45, extraModelLarge45, "large", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=3")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y0, new Vector3f(0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y90, new Vector3f(0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y180, new Vector3f(0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y270, new Vector3f(0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=4")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y0, new Vector3f(-0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y90, new Vector3f(-0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y180, new Vector3f(-0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(
                  baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y270, new Vector3f(-0.9F, 0.0F, 0.0F)
               );
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModelLarge, extraModelLarge, "large", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         }
      } else if (idString.contains("size=2")) {
         if (idString.contains("position=1")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y90);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y180);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y270);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=2")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel45, extraModel45, "medium", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel45, extraModel45, "medium", BlockModelRotation.X0_Y90);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel45, extraModel45, "medium", BlockModelRotation.X0_Y180);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel45, extraModel45, "medium", BlockModelRotation.X0_Y270);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel45, extraModel45, "medium", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=3")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y0, new Vector3f(0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y90, new Vector3f(0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y180, new Vector3f(0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y270, new Vector3f(0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y0, new Vector3f(0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         } else if (idString.contains("position=4")) {
            if (idString.contains("facing=north")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y0, new Vector3f(-0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=east")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y90, new Vector3f(-0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=south")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y180, new Vector3f(-0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else if (idString.contains("facing=west")) {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y270, new Vector3f(-0.5F, 0.0F, 0.0F));
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            } else {
               LoomUnbakedModel newModel = new LoomUnbakedModel(baseModel, extraModel, "medium", BlockModelRotation.X0_Y0);
               this.unbakedCache.put(id, newModel);
               this.loadingStack.addAll(newModel.getDependencies());
            }
         }
      }
   }
}
