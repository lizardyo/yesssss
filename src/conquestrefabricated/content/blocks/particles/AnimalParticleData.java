package com.conquestrefabricated.content.blocks.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Locale;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleOptions.Deserializer;
import net.minecraft.network.FriendlyByteBuf;

public class AnimalParticleData implements ParticleOptions {
   private ParticleType animalParticleType;
   public static final Deserializer<AnimalParticleData> DESERIALIZER = new Deserializer<AnimalParticleData>() {
      public AnimalParticleData read(ParticleType<AnimalParticleData> p_197544_1_, StringReader p_197544_2_) throws CommandSyntaxException {
         return new AnimalParticleData();
      }

      public AnimalParticleData read(ParticleType<AnimalParticleData> p_197543_1_, FriendlyByteBuf p_197543_2_) {
         return new AnimalParticleData();
      }
   };

   public AnimalParticleData(ParticleType animalParticleType) {
      this.animalParticleType = animalParticleType;
   }

   public AnimalParticleData() {
   }

   public ParticleType<AnimalParticleData> getType() {
      return this.animalParticleType;
   }

   public void writeToNetwork(FriendlyByteBuf p_197553_1_) {
   }

   public String writeToString() {
      return String.format(Locale.ROOT, "%s %.2f %i %i %i", this.getType());
   }
}
