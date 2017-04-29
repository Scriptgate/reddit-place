package net.scriptgate.rplace.program;

import net.scriptgate.rplace.BinaryWriter;
import net.scriptgate.rplace.CSVReader;
import net.scriptgate.rplace.Event;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVToBinaryProgram {

    public static final String OUTPUT = "C:/IdeaProjects/reddit-place/src/main/resources/official_diffs.bin";

    public static void main(String[] args) throws Exception {
        List<Event> events = CSVReader.readEvents();
        events.sort((a, b) -> Integer.compare(a.timestamp, b.timestamp));

        Path outputFile = Paths.get(OUTPUT);
        Files.deleteIfExists(outputFile);
        Files.createFile(outputFile);

        BinaryWriter.writeEvents(events, outputFile);
    }

}
