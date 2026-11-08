package com.conquestrefabricated.core.init;

import com.conquestrefabricated.api.painting.PaintingHolder;
import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.content.entities.painting.ModPainting;
import com.conquestrefabricated.content.entities.painting.art.ArtType;
import com.conquestrefabricated.content.entities.painting.art.ModArt;
import com.conquestrefabricated.core.block.data.BlockData;
import com.conquestrefabricated.core.block.data.BlockDataRegistry;
import com.conquestrefabricated.core.block.data.ColorType;
import com.conquestrefabricated.core.group.forge.FamilyGroup;
import com.conquestrefabricated.core.util.log.Log;
import java.util.ArrayList;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TippedArrowItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent.Block;
import net.minecraftforge.client.event.RegisterColorHandlersEvent.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class InitClient {
   @SubscribeEvent
   public static void blockColors(Block event) {
      Log.debug("Registering block colors");

      for (BlockData data : BlockDataRegistry.getInstance()) {
         if (data.getProps().getColorType() == ColorType.GRASS) {
            event.register(
               (state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.get(0.5, 1.0),
               new net.minecraft.world.level.block.Block[]{data.getBlock()}
            );
         } else if (data.getProps().getColorType() == ColorType.FOLIAGE) {
            event.register(
               (state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageFoliageColor(view, pos) : FoliageColor.get(0.5, 1.0),
               new net.minecraft.world.level.block.Block[]{data.getBlock()}
            );
         } else if (data.getProps().getColorType() == ColorType.WATER) {
            event.register((state, view, pos, tintIndex) -> BiomeColors.getAverageWaterColor(view, pos), new net.minecraft.world.level.block.Block[]{data.getBlock()});
         }
      }
   }

   @SubscribeEvent
   public static void itemColors(Item event) {
      Log.debug("Registering item colors");

      for (BlockData data : BlockDataRegistry.getInstance()) {
         if (data.getProps().getColorType() == ColorType.GRASS || data.getProps().getColorType() == ColorType.FOLIAGE) {
            event.register((stack, tintIndex) -> 7117131, new ItemLike[]{data.getItem()});
         }
      }
   }

   @SubscribeEvent
   public static void init(FMLClientSetupEvent event) {
      Log.debug("Registering block render layers");
      BlockDataRegistry.getInstance().forEach(BlockData::addRenders);
   }

   @SubscribeEvent
   public static void buildContents(BuildCreativeModeTabContentsEvent event) {
      FamilyGroup.FAMILY_GROUPS.forEach(familyGroup -> {
         if (familyGroup.cached.isEmpty()) {
            NonNullList<ItemStack> list = NonNullList.create();
            familyGroup.populate(list);
            familyGroup.sorter.apply(list);
            if (event.getTab().equals(CreativeModeTabs.searchTab())) {
               event.acceptAll(list);
            }

            familyGroup.sorter.sort(list);
            familyGroup.cached = new ArrayList<>(list);
         }

         if (event.getTabKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(familyGroup).get())) {
            for (ItemStack item : familyGroup.cached) {
               if (item.getItem() != Items.AIR) {
                  if (item.getItem() instanceof PaintingHolder) {
                     Art<?> art = ModArt.of(ArtType.A1x1_0);
                     ModPainting.getIds().distinct().sorted().forEach(name -> {
                        ModPainting type = ModPainting.fromName(name);
                        ItemStack stack = type.createStack(art);
                        event.accept(stack);
                     });
                  } else if (item.getItem() instanceof TippedArrowItem) {
                     for (Potion potion : BuiltInRegistries.POTION) {
                        if (!potion.getEffects().isEmpty()) {
                           event.accept(PotionUtils.setPotion(new ItemStack(item.getItem()), potion));
                        }
                     }
                  } else {
                     event.accept(item.getItem());
                  }
               }
            }
         }
      });
   }
}
