package com.conquestrefabricated.core.block.properties;

import net.minecraft.util.StringRepresentable;

public enum ParallelConnectionShape implements StringRepresentable {
   ONE("one"),
   EDGE("edge"),
   MIDDLE("middle");

   private final String name;

   private ParallelConnectionShape(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public String getSerializedName() {
      return this.name;
   }
}
