package net.scriptgate.common;

import java.util.List;

import static net.scriptgate.common.Face.ELEMENTS_PER_FACE;

public class Faces {

    public static float[] generateData(List<Face> faces) {
        final int FLOATS_PER_FACE = faces.iterator().next().getNumberOfFloatsPerElement() * ELEMENTS_PER_FACE;
        final int floatDataSize = FLOATS_PER_FACE * faces.size();

        final float[] data = new float[floatDataSize];
        int offset = 0;

        for (Face face : faces) {
            face.addFaceToArray(data, offset);
            offset += FLOATS_PER_FACE;
        }

        return data;
    }

}
