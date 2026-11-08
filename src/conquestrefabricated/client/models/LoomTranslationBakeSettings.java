package com.conquestrefabricated.client.models;

import com.mojang.math.Transformation;
import net.minecraft.client.resources.model.ModelState;
import org.joml.Vector3f;

public class LoomTranslationBakeSettings implements ModelState {
   private final ModelState parent;
   private final Vector3f translation;
   private final Transformation transformation;

   public LoomTranslationBakeSettings(ModelState parent, Vector3f translation) {
      this.parent = parent;
      this.translation = translation;
      this.transformation = new Transformation(translation, null, null, null);
   }

   public LoomTranslationBakeSettings(ModelState parent, float x, float y, float z) {
      this(parent, new Vector3f(x, y, z));
   }

   public Transformation getRotation() {
      Transformation parentTransform = this.parent.getRotation();
      return parentTransform == null ? this.transformation : parentTransform.compose(this.transformation);
   }

   public boolean isUvLocked() {
      return this.parent.isUvLocked();
   }
}
