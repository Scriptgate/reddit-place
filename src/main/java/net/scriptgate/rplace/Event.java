package net.scriptgate.rplace;

public class Event {

    public int timestamp;
    public int x;
    public int y;
    public int color;

    public Event(int timestamp, int x, int y, int color) {
        this.timestamp = timestamp;
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
