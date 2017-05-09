package net.scriptgate.rplace.program;

import net.scriptgate.rplace.BinaryReader;
import net.scriptgate.rplace.BinaryWriter;
import net.scriptgate.rplace.Event3D;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class BinaryToEventProgram {

    public static final String OUTPUT = "C:\\IdeaProjects\\reddit-place\\src\\main\\resources\\3d_diffs.bin";

    public static void main(String[] args) throws Exception {
        List<Event3D> events = BinaryReader.readEvents3D();
        System.out.println("Events read: "+events.size());
        events.sort(sortEvent3DByTimestamp());

        enrichEventsWithHeight(events);

        Path outputFile = Paths.get(OUTPUT);
        Files.deleteIfExists(outputFile);
        Files.createFile(outputFile);

        BinaryWriter.write(events, outputFile);
    }

    private static void enrichEventsWithHeight(List<Event3D> events) {
        Map<String, Float> heightMap = new HashMap<>();

        for (Event3D event : events) {
            String coordinate = event.x + "," + event.y;

            float height = 0.0f;
            if (heightMap.containsKey(coordinate)) {
                height = heightMap.get(coordinate) + 0.2f;
            }
            heightMap.put(coordinate, height);

            event.z = height;
        }
    }

    private static Comparator<Event3D> sortEvent3DByTimestamp() {
        return (a, b) -> Integer.compare(a.timestamp, b.timestamp);
    }

}
