package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.block.trees.Sapling;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.grower.AcaciaTreeGrower;
import net.minecraft.world.level.block.grower.BirchTreeGrower;
import net.minecraft.world.level.block.grower.DarkOakTreeGrower;
import net.minecraft.world.level.block.grower.JungleTreeGrower;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;

public class SaplingsInit {
   public static void init() {
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("alder_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("apple_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("aspen_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("cherry_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("downy_willow_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("gorse_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("grape_vine_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("horse_chestnut_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("larch_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("mallorn_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("orange_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("pear_tree_sapling").manual().with("tree", new OakTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("pine_tree_sapling").manual().with("tree", new OakTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("plum_tree_sapling").manual().with("tree", new OakTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("rowan_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("willow_tree_sapling")
         .manual()
         .with("tree", new OakTreeGrower())
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("oak_sapling").manual().with("tree", new OakTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("birch_sapling").manual().with("tree", new BirchTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("spruce_sapling").manual().with("tree", new SpruceTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("jungle_sapling").manual().with("tree", new JungleTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("acacia_sapling").manual().with("tree", new AcaciaTreeGrower()).register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("dark_oak_sapling")
         .manual()
         .with("tree", new DarkOakTreeGrower())
         .register(TypeList.of(Sapling.class));
   }
}
