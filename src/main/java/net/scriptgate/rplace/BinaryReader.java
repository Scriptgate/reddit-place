package net.scriptgate.rplace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryReader {

    public static List<Event> readEvents() throws IOException {
        String inputFile = "C:/IdeaProjects/reddit-place/src/main/resources/tile_placements.csv";

        List<Event> events = new ArrayList<>();
        try (InputStream is = new FileInputStream(new File(inputFile))) {

            while (is.available() != 0) {
                int timestamp = InputStreamHelper.readUInt32(is);
                int x = InputStreamHelper.readUInt32(is);
                int y = InputStreamHelper.readUInt32(is);
                int color = InputStreamHelper.readUInt32(is);
                events.add(new Event(timestamp, x, y, color));
            }
        }
        return events;
    }

    public static List<Event3D> readEvents3D() throws IOException {
        String inputFile = "C:/IdeaProjects/reddit-place/src/main/resources/official_diffs.bin";

        List<Event3D> events = new ArrayList<>();
        try (InputStream is = new FileInputStream(new File(inputFile))) {

            while (is.available() != 0) {
                int timestamp = InputStreamHelper.readUInt32(is);
                int x = InputStreamHelper.readUInt32(is);
                int y = InputStreamHelper.readUInt32(is);
                int color = InputStreamHelper.readUInt32(is);
                events.add(new Event3D(timestamp, x, y, color));
            }
        }
        return events;
    }
}
