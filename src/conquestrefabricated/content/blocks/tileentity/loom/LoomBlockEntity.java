package com.conquestrefabricated.content.blocks.tileentity.loom;

import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LoomBlockEntity extends BlockEntity {
   private String product = "test";

   public LoomBlockEntity(BlockPos pos, BlockState state) {
      super(TileEntityTypes.LOOM, pos, state);
   }

   public void tick(Level world, BlockPos pos) {
   }

   public void load(CompoundTag nbt) {
      super.load(nbt);
      this.product = nbt.getString("product");
   }

   public void saveAdditional(CompoundTag nbt) {
      nbt.putString("product", this.product);
      super.saveAdditional(nbt);
   }

   public String getProduct() {
      return this.product;
   }

   public void setProduct(String value) {
      this.product = value;
   }

   public Packet<ClientGamePacketListener> getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   public CompoundTag getUpdateTag() {
      return this.saveWithoutMetadata();
   }
}
