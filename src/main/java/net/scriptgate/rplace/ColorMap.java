package net.scriptgate.rplace;

import net.scriptgate.common.Color;
import net.scriptgate.common.Color3f;

import java.util.HashMap;
import java.util.Map;

import static net.scriptgate.common.Color.fromHex;

public class ColorMap {

    private static Map<Integer, Color> colorMap;

    static {
        colorMap = new HashMap<>();
        colorMap.put(0, fromHex("#FFFFFF"));
        colorMap.put(1, fromHex("#E4E4E4"));
        colorMap.put(2, fromHex("#888888"));
        colorMap.put(3, fromHex("#222222"));
        colorMap.put(4, fromHex("#FFA7D1"));
        colorMap.put(5, fromHex("#E50000"));
        colorMap.put(6, fromHex("#E59500"));
        colorMap.put(7, fromHex("#A06A42"));
        colorMap.put(8, fromHex("#E5D900"));
        colorMap.put(9, fromHex("#94E044"));
        colorMap.put(10, fromHex("#02BE01"));
        colorMap.put(11, fromHex("#00D3DD"));
        colorMap.put(12, fromHex("#0083C7"));
        colorMap.put(13, fromHex("#0000EA"));
        colorMap.put(14, fromHex("#CF6EE4"));
        colorMap.put(15, fromHex("#820080"));
    }

    public static Color getColor(Integer index) {
        return colorMap.get(index);
    }

    public static Color3f getColor3f(Integer index) {
        Color color = getColor(index);
        return new Color3f(color.red, color.green, color.blue);
    }

}
