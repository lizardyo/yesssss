package com.conquestrefabricated.content.blocks.group.forge;

import com.conquestrefabricated.core.group.forge.FamilyGroup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class ModGroupsEvent {
   @SubscribeEvent
   public static void registerItemGroups(RegisterEvent event) {
      event.register(
         BuiltInRegistries.CREATIVE_MODE_TAB.key(),
         itemGroupRegisterHelper -> FamilyGroup.stream()
            .forEach(familyGroup -> itemGroupRegisterHelper.register(new ResourceLocation("conquest", familyGroup.label), familyGroup))
      );
   }
}
