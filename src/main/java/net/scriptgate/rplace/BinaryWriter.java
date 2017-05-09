package net.scriptgate.rplace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class BinaryWriter {

    public static void writeEvents(List<Event> events, Path outputFile) throws IOException {
        try (FileOutputStream out = new FileOutputStream(outputFile.toFile())) {
            for (Event event : events) {
                out.write(allocate(16).order(LITTLE_ENDIAN)
                        .putInt(event.timestamp)
                        .putInt(event.x)
                        .putInt(event.y)
                        .putInt(event.color)
                        .array());
            }
            out.flush();
        }
    }

    protected static byte[] integerToUInt32(int i) {
        return allocate(4).order(LITTLE_ENDIAN).putInt(i).array();
    }

    public static void write(List<Event3D> points, Path outputFile) throws IOException {
        try (FileOutputStream out = new FileOutputStream(outputFile.toFile())) {
            for (Event3D point : points) {
                out.write(allocate(16).order(LITTLE_ENDIAN)
                        .putFloat(point.x)
                        .putFloat(point.y)
                        .putFloat(point.z)
                        .putInt(point.color)
                        .array());
            }
            out.flush();
        }
    }
}
