package net.scriptgate.rplace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Event> readEvents() throws IOException {
        String inputFile = "C:/IdeaProjects/reddit-place/src/main/resources/tile_placements.csv";

        List<Event> events = new ArrayList<>();
        try (InputStream is = new FileInputStream(new File(inputFile));
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            skipHeader(reader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                //ts,user,x_coordinate,y_coordinate,color
                int timestamp = (int) (Long.valueOf(data[0])/1000);
                int x = Integer.valueOf(data[2]);
                int y = Integer.valueOf(data[3]);
                int color = Integer.valueOf(data[4]);
                events.add(new Event(timestamp, x,y,color));
            }
        }
        return events;
    }

    public static List<Event> readEvents(int limit) throws IOException {
        String inputFile = "C:/IdeaProjects/reddit-place/src/main/resources/tile_placements.csv";

        List<Event> events = new ArrayList<>();
        try (InputStream is = new FileInputStream(new File(inputFile));
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            skipHeader(reader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                //ts,user,x_coordinate,y_coordinate,color
                int timestamp = (int) (Long.valueOf(data[0])/1000);
                int x = Integer.valueOf(data[2]);
                int y = Integer.valueOf(data[3]);
                int color = Integer.valueOf(data[4]);
                events.add(new Event(timestamp, x,y,color));
            }
        }
        events.sort((a, b) -> Integer.compare(a.timestamp, b.timestamp));
        return events.subList(0, limit);
    }

    private static void skipHeader(BufferedReader reader) throws IOException {
        String header = reader.readLine();
        System.out.println("HEADER: "+header);
    }

}
