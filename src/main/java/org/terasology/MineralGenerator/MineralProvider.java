package org.terasology.MineralGenerator;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.WhiteNoise;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Produces(MineralFacet.class)
@Requires(@Facet(value = SurfaceHeightFacet.class))
public class MineralProvider implements FacetProviderPlugin {

    private Noise noise;

    @Override
    public void setSeed(long seed) {
        noise = new WhiteNoise(seed);
    }

    @Override
    public void process(GeneratingRegion region) {

        Border3D border = region.getBorderForFacet(MineralFacet.class).extendBy(32, 64, 32);
        MineralFacet facet = new MineralFacet(region.getRegion(), border);
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);

        for (BaseVector2i position : surfaceHeightFacet.getWorldRegion().contents()) {
            //Change min and max depth to vary spawn points of minerals
            int maxDepth = 7;
            int minDepth = 1;
            int randNum = (int)((Math.random() * (maxDepth - minDepth)) + minDepth);
            int surfaceHeight = (int) surfaceHeightFacet.getWorld(position);

            if (surfaceHeight >= facet.getWorldRegion().minY() &&
                    surfaceHeight >= -1 * maxDepth &&
                    surfaceHeight <= -1 * minDepth &&
                    surfaceHeight <= facet.getWorldRegion().maxY() &&
                    noise.noise(position.x(), position.y()) > 0.95) {
                if (facet.getWorldRegion().encompasses(position.x(), surfaceHeight - randNum, position.y())) {
                    facet.setWorld(position.x(), surfaceHeight - randNum, position.y(), true);
                }
            }
        }
        region.setRegionFacet(MineralFacet.class, facet);
    }
}
