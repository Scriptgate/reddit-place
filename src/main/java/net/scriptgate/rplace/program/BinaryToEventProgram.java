package net.scriptgate.rplace.program;

import net.scriptgate.rplace.BinaryReader;
import net.scriptgate.rplace.BinaryWriter;
import net.scriptgate.rplace.Event;
import net.scriptgate.rplace.Event3D;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class BinaryToEventProgram {

    public static final String OUTPUT = "C:\\IdeaProjects\\reddit-place\\src\\main\\resources\\3d_diffs.bin";

    public static void main(String[] args) throws Exception {
        List<Event> events = BinaryReader.readEvents();

        Path outputFile = Paths.get(OUTPUT);
        Files.deleteIfExists(outputFile);
        Files.createFile(outputFile);

        Map<String, List<Event>> groupedByCoordinates = groupByCoordinates(events);
        events = null;
        List<Event3D> cubes = mapToEvent3D(groupedByCoordinates);
        cubes.sort(sortEvent3DByTimestamp());
        groupedByCoordinates = null;

        BinaryWriter.write(cubes, outputFile);
    }

    private static Comparator<Event> sortEventByTimestamp() {
        return (a, b) -> Integer.compare(a.timestamp, b.timestamp);
    }

    private static Comparator<Event3D> sortEvent3DByTimestamp() {
        return (a, b) -> Integer.compare(a.timestamp, b.timestamp);
    }

    private static Map<String, List<Event>> groupByCoordinates(List<Event> events) {
        return events.stream().collect(groupingBy(e -> e.x + "," + e.y, toList()));
    }

    private static List<Event3D> mapToEvent3D(Map<String, List<Event>> groupedByCoordinates) {
        List<Event3D> events = new ArrayList<>();
        for (String coordinate : groupedByCoordinates.keySet()) {
            List<Event> eventsAtCoordinate = groupedByCoordinates.get(coordinate);
            eventsAtCoordinate.sort(sortEventByTimestamp());
            float height = 0;
            for (Event event : eventsAtCoordinate) {
                events.add(new Event3D(event.x, height, event.y, event.timestamp, event.color));
                height += 0.2f;
            }
        }
        return events;
    }

}
