package com.conquestrefabricated;

import java.nio.file.Path;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.resource.PathPackResources;

@EventBusSubscriber(
   modid = "conquest",
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class ModBuiltinRP {
   @SubscribeEvent
   public static void onGatherData(AddPackFindersEvent event) {
      if (event.getPackType() == PackType.CLIENT_RESOURCES) {
         ModList.get()
            .getModContainerById("conquest")
            .ifPresent(
               modContainer -> event.addRepositorySource(
                  packConsumer -> {
                     String packId = "rp_crrp";
                     String fullPackId = "conquest:" + packId;
                     Path resourcePath = ModList.get().getModFileById("conquest").getFile().findResource(new String[]{"resourcepacks/" + packId});
                     ResourcesSupplier resourceSupplier = suppliedPackId -> new PathPackResources(fullPackId, true, resourcePath);
                     int currentVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES);
                     Pack pack = Pack.readMetaAndCreate(
                        fullPackId, Component.literal("CRRP"), true, resourceSupplier, PackType.CLIENT_RESOURCES, Position.TOP, PackSource.BUILT_IN
                     );
                     packConsumer.accept(pack);
                  }
               )
            );
         ModList.get()
            .getModContainerById("jei")
            .ifPresent(
               modContainer -> event.addRepositorySource(
                  packConsumer -> {
                     String packId = "conquest_jei";
                     String fullPackId = "conquest:" + packId;
                     Path resourcePath = ModList.get().getModFileById("conquest").getFile().findResource(new String[]{"resourcepacks/" + packId});
                     ResourcesSupplier resourceSupplier = suppliedPackId -> new PathPackResources(fullPackId, true, resourcePath);
                     int currentVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES);
                     Pack pack = Pack.readMetaAndCreate(
                        fullPackId,
                        Component.literal("Conquest JEI Compat"),
                        true,
                        resourceSupplier,
                        PackType.CLIENT_RESOURCES,
                        Position.TOP,
                        PackSource.BUILT_IN
                     );
                     packConsumer.accept(pack);
                  }
               )
            );
         ModList.get()
            .getModContainerById("roughlyenoughitems")
            .ifPresent(
               modContainer -> event.addRepositorySource(
                  packConsumer -> {
                     String packId = "conquest_rei";
                     String fullPackId = "conquest:" + packId;
                     Path resourcePath = ModList.get().getModFileById("conquest").getFile().findResource(new String[]{"resourcepacks/" + packId});
                     ResourcesSupplier resourceSupplier = suppliedPackId -> new PathPackResources(fullPackId, true, resourcePath);
                     int currentVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES);
                     Pack pack = Pack.readMetaAndCreate(
                        fullPackId,
                        Component.literal("Conquest REI Compat"),
                        true,
                        resourceSupplier,
                        PackType.CLIENT_RESOURCES,
                        Position.TOP,
                        PackSource.BUILT_IN
                     );
                     packConsumer.accept(pack);
                  }
               )
            );
      }
   }
}
