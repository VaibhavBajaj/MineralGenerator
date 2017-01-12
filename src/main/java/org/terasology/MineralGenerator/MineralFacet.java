package org.terasology.MineralGenerator;

import org.terasology.math.Region3i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.facets.base.BaseBooleanFieldFacet3D;

public class MineralFacet extends BaseBooleanFieldFacet3D {
    public MineralFacet(Region3i targetRegion, Border3D border) {
        super(targetRegion, border);
    }
}
