package com.conquestrefabricated.client.models;

import java.util.Collection;
import java.util.function.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class LoomUnbakedModel implements UnbakedModel {
   private final UnbakedModel baseModel;
   private final UnbakedModel extraModel;
   private final BlockModelRotation modelRotation;
   private final String size;
   private final Vector3f translation;

   public LoomUnbakedModel(UnbakedModel unbakedModel, UnbakedModel extraModel, String size, BlockModelRotation modelRotation) {
      this(unbakedModel, extraModel, size, modelRotation, null);
   }

   public LoomUnbakedModel(UnbakedModel unbakedModel, UnbakedModel extraModel, String size, BlockModelRotation modelRotation, Vector3f translation) {
      this.baseModel = unbakedModel;
      this.extraModel = extraModel;
      this.modelRotation = modelRotation;
      this.size = size;
      this.translation = translation;
   }

   public Collection<ResourceLocation> getDependencies() {
      return this.baseModel.getDependencies();
   }

   public void resolveParents(Function<ResourceLocation, UnbakedModel> modelLoader) {
      this.baseModel.resolveParents(modelLoader);
   }

   @Nullable
   public BakedModel bake(ModelBaker loader, Function<Material, TextureAtlasSprite> textureGetter, ModelState rotationContainer, ResourceLocation modelId) {
      String[] textureNames = new String[]{
         "loom_weave_white",
         "loom_weave_red",
         "loom_weave_black",
         "loom_weave_gray",
         "loom_weave_light_gray",
         "loom_weave_white",
         "loom_weave_brown",
         "loom_weave_yellow",
         "loom_weave_orange",
         "loom_weave_pink",
         "loom_weave_magenta",
         "loom_weave_purple",
         "loom_weave_blue",
         "loom_weave_light_blue",
         "loom_weave_cyan",
         "loom_weave_green",
         "loom_weave_lime",
         "loom_weave_baotao",
         "loom_weave_berber",
         "loom_weave_black_persian",
         "loom_weave_blue_nain",
         "loom_weave_brown_oriental",
         "loom_weave_celtic_knot",
         "loom_weave_kashmiri",
         "loom_weave_kazakh",
         "loom_weave_kilim",
         "loom_weave_nahavand",
         "loom_weave_red_and_blue_sarouk",
         "loom_weave_red_oriental",
         "loom_weave_red_pazyryk",
         "loom_weave_shirishabad",
         "loom_weave_william_morris",
         "loom_weave_yellow_red_persian"
      };
      TextureAtlasSprite[] sprites = new TextureAtlasSprite[33];

      for (int i = 0; i < textureNames.length; i++) {
         sprites[i] = textureGetter.apply(
            new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation("conquest:block/7_tools/3_utility/loom/weaves/" + this.size + "/" + textureNames[i]))
         );
      }

      ModelState bakeSettings = this.modelRotation;
      if (this.translation != null) {
         bakeSettings = new LoomTranslationBakeSettings(this.modelRotation, this.translation);
      }

      return new LoomBakedModel(
         this.baseModel.bake(loader, textureGetter, bakeSettings, modelId), this.extraModel.bake(loader, textureGetter, bakeSettings, modelId), sprites
      );
   }
}
