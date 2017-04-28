package net.scriptgate.entity;

import net.scriptgate.common.*;

import java.util.ArrayList;
import java.util.List;

public class CubeDataFactory {

    public static float[] generateData(Point3D position, Color color, float width, float height, float depth) {
        //@formatter:off
        final ColorPoint3D frontA = new ColorPoint3D(new Point3D(position.x,         position.y + height, position.z + depth), color);
        final ColorPoint3D frontB = new ColorPoint3D(new Point3D(position.x + width, position.y + height, position.z + depth), color);
        final ColorPoint3D frontC = new ColorPoint3D(new Point3D(position.x,         position.y,          position.z + depth), color);
        final ColorPoint3D frontD = new ColorPoint3D(new Point3D(position.x + width, position.y,          position.z + depth), color);
        final ColorPoint3D backA  = new ColorPoint3D(new Point3D(position.x,         position.y + height, position.z), color);
        final ColorPoint3D backB  = new ColorPoint3D(new Point3D(position.x + width, position.y + height, position.z), color);
//      final ColorPoint3D backC  = new ColorPoint3D(new Point3D(position.x,         position.y,          position.z), color);
        final ColorPoint3D backD  = new ColorPoint3D(new Point3D(position.x + width, position.y,          position.z), color);
        //@formatter:on

        List<Face> faces = new ArrayList<>();
        //@formatter:off
        ColorPoint3DFace FRONT  = new ColorPoint3DFace(frontA, frontB, frontC, frontD);
        ColorPoint3DFace RIGHT  = new ColorPoint3DFace(frontB,  backB, frontD,  backD);
        ColorPoint3DFace TOP    = new ColorPoint3DFace( backA,  backB, frontA, frontB);
        //@formatter:on

        faces.add(FRONT);
        faces.add(RIGHT);
        faces.add(TOP);

        return Faces.generateData(faces);
    }

}
