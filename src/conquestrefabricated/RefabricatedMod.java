package com.conquestrefabricated;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.client.gui.config.forge.ConfigSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkRegistry.ChannelBuilder;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod("conquest")
public class RefabricatedMod {
   private static SimpleChannel CHANNEL;
   public static Boolean localPlantSlowness;
   public static Boolean localPlantBreaking;
   public static Boolean localPassThroughLeaves;

   public RefabricatedMod() {
      ConquestConfig.INSTANCE.getClass();
      CHANNEL = ChannelBuilder.named(new ResourceLocation("conquest", "config_sync"))
         .networkProtocolVersion(() -> "1.0")
         .clientAcceptedVersions(s -> true)
         .serverAcceptedVersions(s -> true)
         .simpleChannel();
      CHANNEL.messageBuilder(ConfigSyncPacket.class, 0, NetworkDirection.PLAY_TO_CLIENT)
         .encoder(ConfigSyncPacket::encode)
         .decoder(ConfigSyncPacket::decode)
         .consumerMainThread(ConfigSyncPacket::handle)
         .add();
      MinecraftForge.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
      if (event.getEntity() instanceof ServerPlayer player) {
         ConfigSyncPacket packet = new ConfigSyncPacket(
            (Boolean)ConquestConfig.INSTANCE.plantSlowness.get(),
            (Boolean)ConquestConfig.INSTANCE.plantBreaking.get(),
            (Boolean)ConquestConfig.INSTANCE.passThroughLeaves.get()
         );
         CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
      }
   }
}
