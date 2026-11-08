package com.conquestrefabricated;

import com.conquestrefabricated.content.gamerules.ModGamerules;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.BooleanValue;
import net.minecraft.world.level.GameRules.Category;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class ModGameRulesInit {
   @SubscribeEvent
   public static void onCommonSetup(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> ModGamerules.BREAK_BLOCKS_ABOVE_DIRT = GameRules.register("breakFoliageCover", Category.UPDATES, BooleanValue.create(true)));
   }
}
