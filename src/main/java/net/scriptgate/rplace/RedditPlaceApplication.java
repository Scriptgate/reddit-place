package net.scriptgate.rplace;

import net.scriptgate.common.Color3f;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.Engine;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;
import net.scriptgate.engine.lwjgl.OpenGLApplicationHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static net.scriptgate.rplace.ColorMap.getColor3f;
import static net.scriptgate.rplace.InputStreamHelper.readUInt32;

public class RedditPlaceApplication implements Application {

    public static final long TOTAL_NUMBER_OF_EVENTS = 16_559_897;

    public static void main(String[] args) {
        new OpenGLApplicationHandler().start(new RedditPlaceApplication());
    }

    private InputStream differenceStream;
    private Map<String, Color3f> world = new HashMap<>();

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        try {
            if (differenceStream != null && differenceStream.available() != 0) {
                int timestampInSeconds = readUInt32(differenceStream);
                int x = readUInt32(differenceStream);
                int y = readUInt32(differenceStream);
                int colorId = readUInt32(differenceStream);

                assert x < 1000;
                assert y < 1000;
                assert colorId < 16;

                world.put(x + "," + y, getColor3f(colorId));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize() {
        Engine.BG_COLOR = Color3f.WHITE;
        try {
            differenceStream = new FileInputStream(new File("C:/IdeaProjects/reddit-place/src/main/resources/official_diffs.bin"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Renderer renderer) {
        world.keySet().forEach(key -> {
            Color3f color = world.get(key);
            String[] position = key.split(",");
            renderer.setColor(color);
            renderer.fillRect(
                    Integer.valueOf(position[0]),
                    Integer.valueOf(position[1]),
                    1, 1);
        });
    }
}
