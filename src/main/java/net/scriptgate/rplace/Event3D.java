package net.scriptgate.rplace;

public class Event3D {

    public final int x;
    public final int y;
    public float z;
    public final int timestamp;
    public final int color;

    public Event3D(int timestamp, int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.timestamp = timestamp;
        this.color = color;
    }

    public Event3D(int timestamp, int x, int y, float z, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.timestamp = timestamp;
        this.color = color;
    }

}
