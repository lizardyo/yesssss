package com.conquestrefabricated.client.models;

import java.util.Collection;
import java.util.function.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class DuplicateDownUnbakedModel implements UnbakedModel {
   private final UnbakedModel baseModel;

   public DuplicateDownUnbakedModel(UnbakedModel unbakedModel) {
      this.baseModel = unbakedModel;
   }

   public Collection<ResourceLocation> getDependencies() {
      return this.baseModel.getDependencies();
   }

   public void resolveParents(Function<ResourceLocation, UnbakedModel> modelLoader) {
      this.baseModel.resolveParents(modelLoader);
   }

   @Nullable
   public BakedModel bake(ModelBaker loader, Function<Material, TextureAtlasSprite> textureGetter, ModelState rotationContainer, ResourceLocation modelId) {
      return new DuplicateDownBakedModel(this.baseModel.bake(loader, textureGetter, rotationContainer, modelId));
   }
}
