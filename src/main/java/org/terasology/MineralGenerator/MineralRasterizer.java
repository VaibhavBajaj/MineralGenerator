package org.terasology.MineralGenerator;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizerPlugin;
import org.terasology.world.generator.plugin.RegisterPlugin;

import java.util.ArrayList;
import java.util.List;


@RegisterPlugin
public class MineralRasterizer implements WorldRasterizerPlugin{

    private List<String> blockList = new ArrayList<>();

    @Override
    public void initialize() {

        // A rather long string of all the blocks in Minerals module
        String[] blocks = {"Abenakiite", "AcanthiteOre", "Alabaster", "Alunite", "Andesite", "Anhydrite", "Anthracite",
                "Arsenic", "Asbestos", "AuriferousQuartz", "Azurite", "Babingtonite", "BandedIron", "Barium", "Basalt",
                "Bauxite", "BauxiteOre", "BauxiteOre1", "Beryllium", "Bismuth", "Bituminous", "BituminousCoalOre",
                "Borax", "Boron", "Brimstone", "Calcite", "Cassiterite", "CassiteriteOre", "Cesium", "ChalcopyriteOre",
                "Chalk", "Chert", "ChlorargyriteOre", "Chromite", "ChrysocollaOre", "ChrysocollaOre2", "Cinnabar",
                "ClayStone", "CoalOre", "Cobaltite", "Conglomerate", "CorundumBlack", "CorundumBlue", "CorundumGreen",
                "CorundumPink", "CorundumRed", "CorundumYellow", "Cryolite", "Dacite", "DarkClay", "Davidite", "Diatomite",
                "Diorite", "Gabbro", "Galena", "Garnierite", "Gneiss", "GoethiteOre", "Granite", "Graphite", "Gypsum",
                "Halite", "HematiteOre", "HematiteOre1", "Hornblende", "Ilmenite", "IronMeteorite", "Jet", "Kaolinite",
                "Kimberlite", "Laterites", "Lignite", "Limestone", "Limonite", "LimoniteOre", "Magnetite", "MagnetiteOre",
                "Marble", "Marcasite", "Mica", "Microcline", "NativeCopperOre", "NativeElectrumOre", "NativeGold",
                "NativeGoldOre", "NativeSilverOre", "OrangeSandStone", "Orpiment", "Orthoclase", "Periclase", "Phyllite",
                "Puddingstone", "Pumice", "PyrargyriteOre", "Pyrite", "Pyrolusite", "RawTin", "Realgar", "RedSandStone",
                "Rhyolite", "Rutile", "RutileOre", "Samarskite", "Satinspar", "Schist", "Selenite", "Serpentine", "Shale",
                "SideriteOre", "Siltstone", "Slate", "Stibnite", "StibniteOre", "Sulphur", "Sylvite", "Talc", "TitaniteOre",
                "Tungsten", "Uraninite", "YellowSandStone"};
        for(String block: blocks) {
            blockList.add(block);
        }
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {

        MineralFacet mineralFacet = chunkRegion.getFacet(MineralFacet.class);

        for (Vector3i position: chunkRegion.getRegion()) {
            if (mineralFacet.getWorld(position)) {
                int randIndex = (int)(Math.random() * blockList.size());
                chunk.setBlock(ChunkMath.calcBlockPos(position),
                        CoreRegistry.get(BlockManager.class).getBlock("Minerals:" + blockList.get(randIndex)));
            }
        }
    }
}
