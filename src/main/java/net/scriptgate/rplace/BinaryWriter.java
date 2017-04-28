package net.scriptgate.rplace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.List;

public class BinaryWriter {

    public static void writeEventsTo(List<Event> events, Path outputFile) throws IOException {
        try (FileOutputStream out = new FileOutputStream(outputFile.toFile())) {
            for (Event event : events) {
                out.write(integerToUInt32(event.timestamp));
                out.write(integerToUInt32(event.x));
                out.write(integerToUInt32(event.y));
                out.write(integerToUInt32(event.color));
            }
            out.flush();
        }
    }

    protected static byte[] integerToUInt32(int i) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array();
    }
}
