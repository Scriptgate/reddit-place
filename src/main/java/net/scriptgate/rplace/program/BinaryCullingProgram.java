package net.scriptgate.rplace.program;

import net.scriptgate.rplace.BinaryReader;
import net.scriptgate.rplace.Event3D;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.scriptgate.rplace.RedditPlaceApplication.TOTAL_NUMBER_OF_EVENTS;

public class BinaryCullingProgram {

    /*
    Events read: 16559897
    Culling cubes removed 68% of cubes (5259056/16559897).
    Cubes without top face: 4801475
    Cubes with top face: 457581

     */

    public static void main(String[] args) throws Exception {
        List<Event3D> events = BinaryReader.readEvents3D();
        System.out.println("Events read: " + events.size());
        events.sort(sortEvent3DByTimestamp());

        Map<Coordinate, Float> heightMap = enrichEventsWithHeight(events);

        int countCubesWithoutTop = 0;
        int countCubesWithTop = 0;
        for (Event3D event : events) {
            boolean isOccluded = false;

            int diagonalOffset = 1;
            Coordinate coordinate = new Coordinate(event.x + diagonalOffset, event.y + diagonalOffset);
            while (heightMap.containsKey(coordinate)) {


                Float height = heightMap.get(coordinate);


                if (event.z + diagonalOffset <= height) {
                    isOccluded = true;
                    break;
                }

                diagonalOffset++;
                coordinate = new Coordinate(event.x + diagonalOffset, event.y + diagonalOffset);
            }

            if (!isOccluded) {
                if (event.z < heightMap.get(new Coordinate(event.x, event.y))) {
                    countCubesWithoutTop++;
                } else {
                    countCubesWithTop++;
                }
            }
        }

        int count = countCubesWithoutTop + countCubesWithTop;
        int percentageRemoved = (int) ((1 - (count * 1.0f / TOTAL_NUMBER_OF_EVENTS)) * 100);

        System.out.println("Culling cubes removed " + percentageRemoved + "% of cubes (" + count + "/" + TOTAL_NUMBER_OF_EVENTS + ").");
        System.out.println("Cubes without top face: " + countCubesWithoutTop);
        System.out.println("Cubes with top face: " + countCubesWithTop);

    }

    private static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }


    private static Map<Coordinate, Float> enrichEventsWithHeight(List<Event3D> events) {
        Map<Coordinate, Float> heightMap = new HashMap<>();

        for (Event3D event : events) {
            Coordinate coordinate = new Coordinate(event.x, event.y);

            float height = 0.0f;
            if (heightMap.containsKey(coordinate)) {
                height = heightMap.get(coordinate) + 0.2f;
            }
            heightMap.put(coordinate, height);

            event.z = height;
        }

        return heightMap;
    }


    private static Comparator<Event3D> sortEvent3DByTimestamp() {
        return (a, b) -> Integer.compare(a.timestamp, b.timestamp);
    }


}
